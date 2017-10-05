package com.delricco.vince.gittest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.eclipse.jgit.api.Git
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
        file.delete()

        // create the directory
        val git = Git.init().setDirectory(file).call()

        println("Repository lives here: " + git.repository.directory)
        println("Current branch is: " + git.repository.branch)

        git.close()
        file.delete()
    }
}
