package com.example.cooljugator.command

import com.example.cooljugator.VerbsImportManager
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
class VerbsImportCommand(private val verbsImportManager: VerbsImportManager) {

    @ShellMethod("Common verbs import command")
    fun import(): Unit {
        verbsImportManager.manageImport()
    }
}