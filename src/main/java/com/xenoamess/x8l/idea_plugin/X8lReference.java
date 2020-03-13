package com.xenoamess.x8l.idea_plugin;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.IElementType;
import org.apache.commons.collections.list.SetUniqueList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.xenoamess.x8l.idea_plugin.X8lAnnotator.I_ELEMENT_TYPES;

public class X8lReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private final String key;

    public X8lReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        List<PsiElement> resultPsiElements = getResultPsiElements();
        List<ResolveResult> results = new ArrayList<>(resultPsiElements.size());
        for (PsiElement psiElement : resultPsiElements) {
            if (psiElement != myElement) {
                results.add(new PsiElementResolveResult(psiElement));
            }
        }
        return results.toArray(new ResolveResult[results.size()]);
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
        resultPsiElements.addAll(
                tryAddJavaElement()
        );
        return resultPsiElements;
    }

    public List<PsiElement> tryAddJavaElement() {
        List<PsiElement> result = SetUniqueList.decorate(new ArrayList<PsiElement>());
        try {
            Project project = myElement.getProject();
            FileType javaFileType =
                    (FileType) Class.forName(
                            "com.intellij.ide.highlighter.JavaFileType"
                    ).getField("INSTANCE").get(null);

            Collection<VirtualFile> virtualFiles =
                    FileTypeIndex.getFiles(javaFileType,
                            GlobalSearchScope.allScope(project));
            for (VirtualFile virtualFile : virtualFiles) {
                PsiFile javaFile = PsiManager.getInstance(project).findFile(virtualFile);
                if (javaFile != null) {
                    result.addAll(
                            X8lUtil.findMostRemoteChildrenOfType(
                                    javaFile,
                                    "\"" + key + "\""
                                    ,
                                    null,
                                    -1
                            )
                    );
                    result.addAll(
                            X8lUtil.findMostRemoteChildrenOfType(
                                    javaFile,
                                    key
                                    ,
                                    null,
                                    -1
                            )
                    );
                }
            }
        } catch (Exception e) {
            //do nothing.
        }
        return new ArrayList<>(result);
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
        List<PsiElement> psiElements = X8lUtil.findAllPsiElements(project);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final PsiElement psiElement : psiElements) {
            if (psiElement.getText() != null && psiElement.getText().length() > 0) {
                variants.add(LookupElementBuilder
                        .create(psiElement).withIcon(X8lDataCenter.X8L_LANGUAGE_ICON)
                        .withTypeText(psiElement.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}