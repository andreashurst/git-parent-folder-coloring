package com.example.gitparentfoldercoloring;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.project.Project;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryChangeListener;
import org.jetbrains.annotations.NotNull;

public class GitRepositoryChangeListener implements GitRepositoryChangeListener {

    @Override
    public void repositoryChanged(@NotNull GitRepository repository) {
        Project project = repository.getProject();

        if (project.isDisposed()) {
            return;
        }

        ProjectView projectView = ProjectView.getInstance(project);
        if (projectView != null) {
            projectView.refresh();
        }
    }
}
