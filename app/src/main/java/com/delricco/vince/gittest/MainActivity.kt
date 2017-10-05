package com.delricco.vince.gittest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
        val git = Git.init().setDirectory(file).call()
        println("Repository lives here: " + git.repository.directory)
        println("Current branch is: " + git.repository.branch)

        val file1 = File(file, "testfile")
        file1.writeText("Hello World!")
        println(file1.absolutePath)

        git.add().addFilepattern(".").call()
        git.printStatusInfo()

        git.close()
        file1.delete()
        file.deleteRecursively()
    }

    fun Git.printStatusInfo() {
        status().call().printInfo()
    }

    fun Status.printInfo() {
        println("Added: " + added)
        println("Changed: " + changed)
        println("Conflicting: " + conflicting)
        println("Conflicting State State: " + conflictingStageState)
        println("Ignored Not In Index: " + ignoredNotInIndex)
        println("Is Clean: " + isClean)
        println("Missing: " + missing)
        println("Modified: " + modified)
        println("Removed: " + removed)
        println("Uncommitted Changes: " + uncommittedChanges)
        println("Untracked: " + untracked)
        println("Untracked Folders: " + untrackedFolders)
    }
}
