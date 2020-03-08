package com.xenoamess.x8l.idea_plugin;

import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.SmartList;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.psi.X8lFile;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
import org.apache.commons.collections.list.SetUniqueList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class X8lUtil {
    @NotNull
    public static List<PsiElement> findAllPsiElements(Project project) {
        List<PsiElement> result = new SmartList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(X8lFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            X8lFile x8lFile = (X8lFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (x8lFile != null) {
                result.addAll(findAllPsiElements(x8lFile));
            }
        }
        return result;
    }

    @NotNull
    public static Collection<PsiElement> findAllPsiElements(PsiElement element) {
        if (element == null) return Collections.emptyList();
        List<PsiElement> result = new ArrayList<>();
        for (PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
            Collection<PsiElement> childResult = findAllPsiElements(child);
            if (!childResult.isEmpty()) {
                result.addAll(childResult);
            }
        }
        result.add(element);
        return result;
//        return PsiTreeUtil.findChildrenOfType(element, PsiElement.class);
    }

    @NotNull
    public static List<PsiElement> findMostRemotePsiElements(Project project, String string, IElementType iElementType) {
        List<PsiElement> result = new SmartList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(X8lFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            X8lFile x8lFile = (X8lFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (x8lFile != null) {
                result.addAll(findMostRemoteChildrenOfType(x8lFile, string, iElementType, X8L_GET_CHILD_ALL));
            }
        }
        return result;
    }

    @NotNull
    public static List<PsiElement> findMostRemotePsiElementsIncludingTranscode(Project project, String string, IElementType iElementType) {
        final List<PsiElement> result = SetUniqueList.decorate(new ArrayList<PsiElement>());

        result.addAll(
                X8lUtil.findMostRemotePsiElements(project, X8lTree.untranscode(string), iElementType)
        );
        result.addAll(
                X8lUtil.findMostRemotePsiElements(project, string, iElementType)
        );
        result.addAll(
                X8lUtil.findMostRemotePsiElements(project, X8lTree.transcodeKeyAndValue(string), iElementType)
        );
        result.addAll(
                X8lUtil.findMostRemotePsiElements(project, X8lTree.transcodeText(string), iElementType)
        );
        result.addAll(
                X8lUtil.findMostRemotePsiElements(project, X8lTree.transcodeComment(string), iElementType)
        );
        return new ArrayList<>(result);
    }

    public static final int X8L_GET_CHILD_ALL = -1;
    public static final int X8L_GET_CHILD_NONE = 0;

    /**
     * @param element      base element
     * @param string       text string, if==null then can be any string.
     * @param iElementType iElementType, if==null then can be any types.
     * @param requiredNum  requiredNum, if requiredNum>0 then will return at least requiredNum number of elements(instead all of elements)
     *                     if do not have so many elements then return all of them.
     * @return
     */
    @NotNull
    public static List<PsiElement> findMostRemoteChildrenOfType(@Nullable PsiElement element, @Nullable String string, @Nullable IElementType iElementType, int requiredNum) {
        if (element == null) return Collections.emptyList();
        if (requiredNum == 0) {
            return Collections.emptyList();
        }

        List<PsiElement> result = null;

        for (PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
            int childRequiredNum = calculateRequiredNum(requiredNum, result);
            if (childRequiredNum == 0) {
                break;
            }
            List<PsiElement> childResult = findMostRemoteChildrenOfType(child, string, iElementType, childRequiredNum);
            if (!childResult.isEmpty()) {
                if (result == null) {
                    result = new SmartList<>();
                }
                result.addAll(childResult);
            }
        }

        if (result != null) {
            return result;
        }

        if (iElementType == null || element.getNode().getElementType().equals(iElementType)) {
            if (string == null || string.equals(element.getText())) {
                if (result == null) {
                    result = new SmartList<>();
                }
                result.add(element);
            }
        }

        return result == null ? Collections.emptyList() : result;
    }

    /**
     * @param element      base element
     * @param string       text string, if==null then can be any string.
     * @param iElementType iElementType, if==null then can be any types.
     * @param requiredNum  requiredNum, if requiredNum>0 then will return at least requiredNum number of elements(instead all of elements)
     *                     if do not have so many elements then return all of them.
     * @return
     */
    @NotNull
    public static List<PsiElement> findMostNearChildrenOfType(@Nullable PsiElement element, @Nullable String string, @Nullable IElementType iElementType, int requiredNum) {
        if (element == null) return Collections.emptyList();
        if (requiredNum == 0) {
            return Collections.emptyList();
        }

        List<PsiElement> result = null;

        if (iElementType == null || element.getNode().getElementType().equals(iElementType)) {
            if (string == null || string.equals(element.getText())) {
                if (result == null) {
                    result = new SmartList<>();
                }
                result.add(element);
            }
        }

        if (result != null) {
            return result;
        }

        for (PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
            int childRequiredNum = calculateRequiredNum(requiredNum, result);
            if (childRequiredNum == 0) {
                break;
            }
            List<PsiElement> childResult = findMostNearChildrenOfType(child, string, iElementType, childRequiredNum);
            if (!childResult.isEmpty()) {
                if (result == null) {
                    result = new SmartList<>();
                }
                result.addAll(childResult);
            }
        }

        return result == null ? Collections.emptyList() : result;
    }

    protected static int calculateRequiredNum(int requiredNum, List list) {
        int listSize = getSize(list);
        if (requiredNum < 0) {
            return -1;
        }
        if (listSize >= requiredNum) {
            return 0;
        }
        return requiredNum - listSize;
    }

    protected static int getSize(List list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

//    public static List<X8lProperty> findProperties(Project project) {
//        List<X8lProperty> result = new ArrayList<X8lProperty>();
//        Collection<VirtualFile> virtualFiles =
//                FileTypeIndex.getFiles(X8lFileType.INSTANCE, GlobalSearchScope.allScope(project));
//        for (VirtualFile virtualFile : virtualFiles) {
//            X8lFile simpleFile = (X8lFile) PsiManager.getInstance(project).findFile(virtualFile);
//            if (simpleFile != null) {
//                X8lProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, X8lProperty.class);
//                if (properties != null) {
//                    Collections.addAll(result, properties);
//                }
//            }
//        }
//        return result;
//    }

    public static PsiElement createX8lFileFromString(CharSequence text) {
//        ParserDefinition definition = LanguageParserDefinitions.INSTANCE.forLanguage(X8lLanguage.INSTANCE);
//        PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(definition, definition.createLexer(null), text);
        IElementType type = X8lTypes.ROOT_NODE_CHILDREN_AREA;
        Language language = type.getLanguage();
        ParserDefinition parserDefinition = LanguageParserDefinitions.INSTANCE.forLanguage(language);
        assert parserDefinition != null : "No parser definition for language " + language;
        Lexer lexer = parserDefinition.createLexer(null);
        PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(parserDefinition, lexer, text);
        ASTNode node = parserDefinition.createParser(null).parse(type, builder);

        PsiElement psi = node.getPsi();
        assert psi != null : text;
        return psi;
    }

    @NotNull
    public static String getStringFromElement(@Nullable PsiElement psiElement) {
        if (psiElement == null) {
            return "";
        }

        String string = null;

        if (psiElement instanceof PsiLiteralValue) {
            Object valueObject = ((PsiLiteralValue) psiElement).getValue();
            if (valueObject != null) {
                string = valueObject.toString();
            }
        }

        if (string == null) {
            string = psiElement.getText();
        }

        return string;
    }
}