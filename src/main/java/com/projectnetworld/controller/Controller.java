package com.projectnetworld.controller;

import com.projectnetworld.model.SmartStringCollator;
import com.projectnetworld.view.UserInterface;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Controller {
    UserInterface userInterface = new UserInterface();
    SmartStringCollator collator = new SmartStringCollator(Locale.getDefault());

    public void smartSortApplication(){
        userInterface.writeInstructions();
        List<String> lines = userInterface.getUserInput();
        lines.sort(collator);
        userInterface.writeSolution(lines);
        userInterface.writeContactInformation();
    }
}
