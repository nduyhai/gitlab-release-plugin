package com.github.nduyhai.gitlabreleaseplugin.toolWindow

import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import com.github.nduyhai.gitlabreleaseplugin.GitlabReleaseBundle
import com.github.nduyhai.gitlabreleaseplugin.services.GitlabReleaseService
import javax.swing.JButton


class GitlabReleaseWindowFactory : ToolWindowFactory {

    init {
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val gitlabReleaseWindow = GitlabReleaseWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(gitlabReleaseWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class GitlabReleaseWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<GitlabReleaseService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {
            val label = JBLabel(GitlabReleaseBundle.message("randomLabel", "?"))

            add(label)
            add(JButton(GitlabReleaseBundle.message("shuffle")).apply {
                addActionListener {
                    label.text = GitlabReleaseBundle.message("randomLabel", service.getRandomNumber())
                }
            })
        }
    }
}
