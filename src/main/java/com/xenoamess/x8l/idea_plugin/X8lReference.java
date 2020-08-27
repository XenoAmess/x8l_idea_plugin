package com.xenoamess.x8l.idea_plugin;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.AttributeValueSelfReference;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.psi.X8lPsiElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.list.SetUniqueList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static com.xenoamess.x8l.idea_plugin.X8lAnnotator.I_ELEMENT_TYPES;
import static com.xenoamess.x8l.idea_plugin.X8lUtil.createSet;

/**
 * @author XenoAmess
 */
public class X8lReference extends AttributeValueSelfReference implements PsiPolyVariantReference {
    static final transient FileType JAVA_FILE_TYPE = tryGetJavaFileType();
    static final transient Set<IElementType> JAVA_ELEMENT_TYPE_SET = tryGetJavaElementType();
    static final transient Set<Class> ALLOWED_CLASS_SET = createSet(PsiIdentifier.class, PsiLiteralValue.class);

    private static FileType tryGetJavaFileType() {
        FileType result;
        try {
            result =
                    (FileType) Class.forName("com.intellij.ide.highlighter.JavaFileType")
                            .getField("INSTANCE").get(null);
        } catch (Exception ignored) {
            result = null;
        }
        return result;
    }

    private static Set<IElementType> tryGetJavaElementType() {
        Set<IElementType> result = new HashSet<>();
        if (JAVA_FILE_TYPE == null) {
            return result;
        }
        try {
            Class c = Class.forName("com.intellij.psi.JavaTokenType");
            result.add((IElementType) c.getField("IDENTIFIER").get(null));
            result.add((IElementType) c.getField("STRING_LITERAL").get(null));
        } catch (Exception ignored) {
        }
        return result;
    }

    private final String key;

    public X8lReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(0, textRange.getEndOffset() - textRange.getStartOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        if ("p3c_config.x8l".equals(myElement.getContainingFile().getName())) {
            return new ResolveResult[0];
        }
        List<PsiElement> resultPsiElements = getResultPsiElements();
        List<ResolveResult> results = new ArrayList<>(resultPsiElements.size());
        for (PsiElement psiElement : resultPsiElements) {
            if (psiElement != myElement) {
                results.add(new PsiElementResolveResult(psiElement));
            }
        }
        return results.toArray(new ResolveResult[0]);
    }

    protected List<PsiElement> getResultPsiElements() {
        /*
         * right now the algorithm is running for full O(4n)
         * we can change it to <= O(4n), but I just keep it like this for readability.
         */
        List<PsiElement> resultPsiElements = new ArrayList<>();
        for (IElementType iElementType : I_ELEMENT_TYPES) {
            final List<PsiElement> elements =
                    X8lUtil.findMostRemotePsiElementsIncludingTranscode(myElement.getProject(), key, iElementType);
            for (PsiElement psiElement : elements) {
                if (psiElement instanceof PsiNameIdentifierOwner) {
                    resultPsiElements.add(psiElement);
                }
            }
        }
        if (this.myElement instanceof X8lPsiElement) {
            if (JAVA_FILE_TYPE != null) {
                resultPsiElements.addAll(
                        getJavaElement()
                );
            }
        }
        return resultPsiElements;
    }

    private List<PsiElement> getJavaElement() {
        List<PsiElement> result = new ArrayList<>();
        SetUniqueList<PsiElement> setUniqueList = SetUniqueList.setUniqueList(result);
        Project project = myElement.getProject();

        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(JAVA_FILE_TYPE,
                        GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiFile javaFile = PsiManager.getInstance(project).findFile(virtualFile);
            if (javaFile != null) {
                setUniqueList.addAll(
                        X8lUtil.findMostRemoteChildrenOfType(
                                javaFile,
                                "\"" + key + "\"",
                                JAVA_ELEMENT_TYPE_SET,
                                -1
                        )
                );
                setUniqueList.addAll(
                        X8lUtil.findMostRemoteChildrenOfType(
                                javaFile,
                                key,
                                JAVA_ELEMENT_TYPE_SET,
                                -1
                        )
                );
            }
        }
        return result;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length >= 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<PsiElement> psiElements = X8lUtil.findAllPsiElementsForClass(project, ALLOWED_CLASS_SET);
        List<LookupElement> variants = new ArrayList<>();
        for (final PsiElement psiElement : psiElements) {
            variants.add(
                    LookupElementBuilder
                            .create(psiElement)
                            .withIcon(X8lDataCenter.X8L_LANGUAGE_ICON)
                            .withTypeText(psiElement.getContainingFile().getName())
            );
        }
        return variants.toArray();
    }
}