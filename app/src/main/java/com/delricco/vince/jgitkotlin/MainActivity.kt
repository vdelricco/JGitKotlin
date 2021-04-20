package com.delricco.vince.jgitkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.Status
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initGitRepo()
    }

    private fun initGitRepo() {
        // prepare a new folder
        val file = File(applicationContext.filesDir, "testrepo")

        // create the directory
        appendInfo("Initializing git repo...")
        val git = Git.init().setDirectory(file).call()
        appendInfo("Repository lives here: ${git.repository.directory}")
        appendInfo("Current branch is: ${git.repository.branch}")

        appendInfo("Creating test file and adding to our git repo...")
        val file1 = File(file, "testfile")
        file1.writeText("Hello World!")
        appendInfo("File created: ${file1.absolutePath}")

        git.add().addFilepattern(".").call()
        appendInfo(git.getStatusInfo())

        git.close()
        file1.delete()
        file.deleteRecursively()
    }

    fun appendInfo(string: String) {
        println(string)
        tvGitOutput.append("$string\n")
    }

    fun Git.getStatusInfo() = status().call().getInfo()

    fun Status.getInfo() =
        "Added: $added\n" +
        "Changed: $changed\n" +
        "Conflicting: $conflicting\n" +
        "Conflicting State State: $conflictingStageState\n" +
        "Ignored Not In Index: $ignoredNotInIndex\n" +
        "Is Clean: $isClean\n" +
        "Missing: $missing\n" +
        "Modified: $modified\n" +
        "Removed: $removed\n" +
        "Uncommitted Changes: $uncommittedChanges\n" +
        "Untracked: $untracked\n" +
        "Untracked Folders: $untrackedFolders\n"
}
