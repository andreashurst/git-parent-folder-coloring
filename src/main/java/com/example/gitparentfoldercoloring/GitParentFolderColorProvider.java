package com.example.gitparentfoldercoloring;

import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.ide.projectView.impl.nodes.PsiDirectoryNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.JBColor;
import git4idea.changes.GitChangeUtils;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryManager;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Collection;

public class GitParentFolderColorProvider implements ProjectViewNodeDecorator {

    @Override
    public void decorate(@NotNull PackageDependenciesNode node, @NotNull ColoredTreeCellRenderer cellRenderer) {
        // This method is for package dependencies view
    }

    @Override
    public void decorate(@NotNull com.intellij.ide.projectView.ProjectViewNode<?> node,
                        @NotNull com.intellij.ide.projectView.PresentationData data) {

        if (!(node instanceof PsiDirectoryNode)) {
            return;
        }

        PsiDirectoryNode dirNode = (PsiDirectoryNode) node;
        Project project = node.getProject();

        if (project == null || project.isDisposed()) {
            return;
        }

        VirtualFile directory = dirNode.getVirtualFile();
        if (directory == null || !directory.isValid()) {
            return;
        }

        if (hasGitChangesInSubtree(project, directory)) {
            // Use a distinctive color for folders with changes
            Color gitModifiedColor = JBColor.namedColor("VersionControl.FileHistory.Commit.selectedBranchBackground",
                                                       new JBColor(new Color(0xFF, 0xE4, 0xE1), new Color(0x5C, 0x2A, 0x2A)));
            data.setForcedTextForeground(gitModifiedColor);

            // Add visual indicator
            String currentText = data.getPresentableText();
            if (currentText != null && !currentText.endsWith(" *")) {
                data.setPresentableText(currentText + " *");
            }
        }
    }

    private boolean hasGitChangesInSubtree(@NotNull Project project, @NotNull VirtualFile directory) {
        GitRepositoryManager gitManager = GitRepositoryManager.getInstance(project);
        GitRepository repository = gitManager.getRepositoryForFile(directory);

        if (repository == null) {
            return false;
        }

        try {
            // Get all changed files in the repository
            Collection<VirtualFile> changedFiles = GitChangeUtils.getDirtyFiles(repository, false);

            // Check if any changed file is within this directory or its subdirectories
            String directoryPath = directory.getPath();

            for (VirtualFile changedFile : changedFiles) {
                String changedFilePath = changedFile.getPath();
                if (changedFilePath.startsWith(directoryPath + "/")) {
                    return true;
                }
            }

            // Also check staged files
            Collection<VirtualFile> stagedFiles = GitChangeUtils.getStagedFiles(repository);
            for (VirtualFile stagedFile : stagedFiles) {
                String stagedFilePath = stagedFile.getPath();
                if (stagedFilePath.startsWith(directoryPath + "/")) {
                    return true;
                }
            }

        } catch (Exception e) {
            // If we can't determine Git status, don't color the folder
            return false;
        }

        return false;
    }
}
