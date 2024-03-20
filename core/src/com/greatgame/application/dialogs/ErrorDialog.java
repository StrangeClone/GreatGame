package com.greatgame.application.dialogs;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.greatgame.application.Mode;

public class ErrorDialog extends Dialog {
    public ErrorDialog(String text) {
        super("Error", Mode.skin);
        this.getContentTable().add(new Label(text, getSkin()));
        this.button("Cancel", false);
    }
}
