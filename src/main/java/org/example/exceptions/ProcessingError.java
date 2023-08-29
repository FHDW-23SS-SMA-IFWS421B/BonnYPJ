package org.example.exceptions;

import org.example.IOHandler;

public class ProcessingError extends Exception {
    public ProcessingError() {
        System.out.println("Es gabe einen Fehler bei der Verarbeitung des Befehls.");;
    }
}

