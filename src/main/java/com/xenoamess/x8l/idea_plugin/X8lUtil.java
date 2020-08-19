package com.xenoamess.x8l.idea_plugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilderFactory;
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
import com.xenoamess.x8l.psi.X8lFile;
import com.xenoamess.x8l.psi.X8lTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author XenoAmess
 */
public class X8lUtil {
    @SafeVarargs
    public static <T> Set<T> createSet(T... ts) {
        return new HashSet<>(Arrays.asList(ts));
    }

    @NotNull
    public static List<PsiElement> findAllPsiElements(Project project) {
        if (project == null) {
            return Collections.emptyList();
        }
        PsiManager psiManager = PsiManager.getInstance(project);
        final ConcurrentLinkedDeque<PsiElement> result = new ConcurrentLinkedDeque<>();

        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(X8lFileType.INSTANCE, GlobalSearchScope.allScope(project));

        virtualFiles.parallelStream().forEach(
                new Consumer<VirtualFile>() {
                    @Override
                    public void accept(VirtualFile virtualFile) {
                        X8lFile x8lFile = (X8lFile) (psiManager.findFile(virtualFile));
                        if (x8lFile != null) {
                            result.addAll(findAllPsiElements(x8lFile));
                        }
                    }
                }
        );
        return new ArrayList<>(result);
    }
//    @NotNull
//    public static List<PsiElement> findAllPsiElements(Project project) {
//        if (project == null) {
//            return Collections.emptyList();
//        }
//        PsiManager psiManager = PsiManager.getInstance(project);
//        List<PsiElement> result = new SmartList<>();
//        Collection<VirtualFile> virtualFiles =
//                FileTypeIndex.getFiles(X8lFileType.INSTANCE, GlobalSearchScope.allScope(project));
//
//        final ExecutorService executorService = Executors.newCachedThreadPool();
//        final List<Callable<Collection<PsiElement>>> returnValueList = new ArrayList<>();
//        for (VirtualFile virtualFile : virtualFiles) {
//            final VirtualFile tmpVirtualFile = virtualFile;
//            returnValueList.add(
//                    new Callable<Collection<PsiElement>>() {
//                        @Override
//                        public Collection<PsiElement> call() throws Exception {
//                            X8lFile x8lFile = (X8lFile) (psiManager.findFile(tmpVirtualFile));
//                            if (x8lFile != null) {
//                                return findAllPsiElements(x8lFile);
//                            }
//                            return Collections.emptyList();
//                        }
//                    }
//            );
//        }
//
//        try {
//            List<Future<Collection<PsiElement>>> resultFuture = executorService.invokeAll(returnValueList);
//            for (Future<Collection<PsiElement>> au : resultFuture) {
//                result.addAll(au.get());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        executorService.shutdown();
//
//        return result;
//    }

    @NotNull
    public static Collection<PsiElement> findAllPsiElements(PsiElement element) {
        if (element == null) {
            return Collections.emptyList();
        }
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

//    @NotNull
//    public static Collection<PsiElement> findAllPsiElements(PsiElement element) {
//        if (element == null) {
//            return Collections.emptyList();
//        }
//        List<PsiElement> result = new SmartList<>();
//        PsiElement[] children =
//                element.getChildren();
//
//        final ExecutorService executorService = Executors.newCachedThreadPool();
//        final List<Callable<Collection<PsiElement>>> returnValueList = new ArrayList<>();
//        for (PsiElement child : children) {
//            final PsiElement tmpChild = child;
//            returnValueList.add(
//                    new Callable<Collection<PsiElement>>() {
//                        @Override
//                        public Collection<PsiElement> call() throws Exception {
//                            if (tmpChild != null) {
//                                return findAllPsiElements(tmpChild);
//                            }
//                            return Collections.emptyList();
//                        }
//                    }
//            );
//        }
//
//        try {
//            List<Future<Collection<PsiElement>>> resultFuture = executorService.invokeAll(returnValueList);
//            for (Future<Collection<PsiElement>> au : resultFuture) {
//                result.addAll(au.get());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        executorService.shutdown();
//
//        return result;
//    }

    @NotNull
    public static List<PsiElement> findMostRemotePsiElements(Project project, String string,
                                                             IElementType iElementType) {
        return findMostRemotePsiElements(project, createSet(string), iElementType);
    }

    @NotNull
    public static List<PsiElement> findMostRemotePsiElements(Project project, Set<String> strings,
                                                             IElementType iElementType) {
        List<PsiElement> result = new SmartList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(X8lFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            X8lFile x8lFile = (X8lFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (x8lFile != null) {
                result.addAll(findMostRemoteChildrenOfType(x8lFile, strings, iElementType, X8L_GET_CHILD_ALL));
            }
        }
        return result;
    }

    @NotNull
    public static List<PsiElement> findMostRemotePsiElementsIncludingTranscode(@NotNull final Project project,
                                                                               @NotNull final String string,
                                                                               IElementType iElementType) {
        return findMostRemotePsiElementsIncludingTranscode(
                project
                , createSet(
                        string
                        , X8lTree.untranscode(string)
                        , X8lTree.transcodeKey(string)
                        , X8lTree.transcodeValue(string)
                        , X8lTree.transcodeText(string)
                        , X8lTree.transcodeComment(string)
                )
                , iElementType);
    }

    @NotNull
    public static List<PsiElement> findMostRemotePsiElementsIncludingTranscode(@NotNull final Project project,
                                                                               @NotNull final Set<String> strings,
                                                                               IElementType iElementType) {

        return X8lUtil.findMostRemotePsiElements(project, strings, iElementType);
    }

    public static final int X8L_GET_CHILD_ALL = -1;
    @SuppressWarnings("unused")
    public static final int X8L_GET_CHILD_NONE = 0;

    /**
     * @param element      base element
     * @param string       text string, if==null then can be any string.
     * @param iElementType iElementType, if==null then can be any types.
     * @param requiredNum  requiredNum, if requiredNum&gt;0 then will return at least requiredNum number of elements
     *                     (instead all of elements)
     *                     if do not have so many elements then return all of them.
     * @return PsiElements
     */
    @NotNull
    public static List<PsiElement> findMostRemoteChildrenOfType(
            @Nullable PsiElement element,
            @Nullable String string,
            @Nullable IElementType iElementType,
            int requiredNum
    ) {
        return findMostRemoteChildrenOfType(
                element,
                (string == null ? null : createSet(string)),
                iElementType,
                requiredNum
        );
    }

    /**
     * @param element      base element
     * @param strings      text string, if==null then can be any string.
     * @param iElementType iElementType, if==null then can be any types.
     * @param requiredNum  requiredNum, if requiredNum&gt;0 then will return at least requiredNum number of elements
     *                     (instead all of elements)
     *                     if do not have so many elements then return all of them.
     * @return PsiElements
     */
    @NotNull
    public static List<PsiElement> findMostRemoteChildrenOfType(@Nullable PsiElement element,
                                                                @Nullable Set<String> strings
            , @Nullable IElementType iElementType, int requiredNum) {
        if (element == null) {
            return Collections.emptyList();
        }
        if (requiredNum == 0) {
            return Collections.emptyList();
        }

        List<PsiElement> result = null;

        for (PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
            int childRequiredNum = calculateRequiredNum(requiredNum, result);
            if (childRequiredNum == 0) {
                break;
            }
            List<PsiElement> childResult = findMostRemoteChildrenOfType(child, strings, iElementType, childRequiredNum);
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
            if (strings == null || strings.contains(element.getText())) {
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
     * @param requiredNum  requiredNum, if requiredNum&gt;0 then will return at least requiredNum number of elements
     *                     (instead all of elements)
     *                     if do not have so many elements then return all of them.
     * @return PsiElements
     */
    @NotNull
    public static List<PsiElement> findMostNearChildrenOfType(
            @Nullable PsiElement element,
            @Nullable String string,
            @Nullable IElementType iElementType,
            int requiredNum
    ) {
        return findMostNearChildrenOfType(
                element,
                (string == null ? null : createSet(string)),
                iElementType,
                requiredNum
        );
    }

    /**
     * @param element      base element
     * @param strings      text string, if==null then can be any string.
     * @param iElementType iElementType, if==null then can be any types.
     * @param requiredNum  requiredNum, if requiredNum&gt;0 then will return at least requiredNum number of elements
     *                     (instead all of elements)
     *                     if do not have so many elements then return all of them.
     * @return PsiElements
     */
    @NotNull
    public static List<PsiElement> findMostNearChildrenOfType(@Nullable PsiElement element,
                                                              @Nullable Set<String> strings,
                                                              @Nullable IElementType iElementType, int requiredNum) {
        if (element == null) {
            return Collections.emptyList();
        }
        if (requiredNum == 0) {
            return Collections.emptyList();
        }

        List<PsiElement> result = null;

        if (iElementType == null || element.getNode().getElementType().equals(iElementType)) {
            if (strings == null || strings.contains(element.getText())) {
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
            List<PsiElement> childResult = findMostNearChildrenOfType(child, strings, iElementType, childRequiredNum);
            if (!childResult.isEmpty()) {
                if (result == null) {
                    result = new SmartList<>();
                }
                result.addAll(childResult);
            }
        }

        return result == null ? Collections.emptyList() : result;
    }

    protected static <T> int calculateRequiredNum(int requiredNum, List<T> list) {
        int listSize = getSize(list);
        if (requiredNum < 0) {
            return -1;
        }
        if (listSize >= requiredNum) {
            return 0;
        }
        return requiredNum - listSize;
    }

    protected static <T> int getSize(List<T> list) {
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
//        PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(definition, definition.createLexer(null)
//        , text);
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
            if (valueObject instanceof String) {
                string = (String) valueObject;
            }
        }

        if (string == null) {
            string = psiElement.getText();
        }

        return string;
    }
}