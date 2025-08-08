package com.example.gitparentfoldercoloring;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.ChangeListListener;
import com.intellij.openapi.vcs.changes.ChangeListManager;
import com.intellij.openapi.vcs.changes.LocalChangeList;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@Service(Service.Level.PROJECT)
public final class GitChangeTracker implements ChangeListListener {

    private final Project project;

    public GitChangeTracker(@NotNull Project project) {
        this.project = project;
        ChangeListManager.getInstance(project).addChangeListListener(this);
    }

    @Override
    public void changeListsChanged() {
        refreshProjectView();
    }

    @Override
    public void changeListUpdateDone() {
        refreshProjectView();
    }

    @Override
    public void changesRemoved(@NotNull Collection<? extends LocalChangeList> lists) {
        refreshProjectView();
    }

    @Override
    public void changesAdded(@NotNull Collection<? extends LocalChangeList> lists) {
        refreshProjectView();
    }

    @Override
    public void unchangedFileStatusChanged() {
        refreshProjectView();
    }

    private void refreshProjectView() {
        if (project.isDisposed()) {
            return;
        }

        ProjectView projectView = ProjectView.getInstance(project);
        if (projectView != null) {
            projectView.refresh();
        }
    }
}
