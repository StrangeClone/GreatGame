package com.greatgame.actions;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.greatgame.application.Mode;
import com.greatgame.environment.Action;
import com.greatgame.environment.Behaviour;
import com.greatgame.explorationBehaviourState.ExplorationBehaviourState;

public class ExplorationModeAllowedActionDialog extends Dialog {

    public ExplorationModeAllowedActionDialog(Behaviour selectedBehaviour) {
        super("", Mode.skin);

        text(removeUnderscores(selectedBehaviour.getType()));
        getContentTable().row();

        for(Action action : selectedBehaviour.getAllowedActions()) {
            com.badlogic.gdx.scenes.scene2d.ui.Button button = new TextButton(action.getName(), getSkin());
            if(!action.validate()) {
                button.setDisabled(true);
            }
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!button.isDisabled()) {
                        ((ExplorationBehaviourState) selectedBehaviour.getEnvironment().getPlayer().getState()).
                                setAction(action);
                        action.start();
                        ExplorationModeAllowedActionDialog.this.remove();
                    }
                }
            });
            getContentTable().add(button).row();
        }

        button("Back");
    }

    static String removeUnderscores(String s) {
        StringBuilder result = new StringBuilder();
        for (Character c : s.toCharArray()) {
            result.append(c == '_' ? ' ' : c);
        }
        return result.toString();
    }
}
