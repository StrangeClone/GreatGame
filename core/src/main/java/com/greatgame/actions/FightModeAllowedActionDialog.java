package com.greatgame.actions;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.greatgame.application.Mode;
import com.greatgame.environment.Action;
import com.greatgame.environment.Behaviour;
import com.greatgame.fightBehaviourState.FightBehaviourState;

import static com.greatgame.actions.ExplorationModeAllowedActionDialog.removeUnderscores;

public class FightModeAllowedActionDialog extends Dialog {

    public FightModeAllowedActionDialog(Behaviour selectedBehaviour) {
        super("", Mode.skin);

        text(removeUnderscores(selectedBehaviour.getType()));
        getContentTable().row();

        for(Action action : selectedBehaviour.getAllowedActions()) {
            Button button = new TextButton(action.getName(), getSkin());
            if(!action.validate()) {
                button.setDisabled(true);
            }
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!button.isDisabled()) {
                        FightBehaviourState state = (FightBehaviourState) selectedBehaviour.
                                getEnvironment().getPlayer().getState();
                        state.startAction(action);
                        FightModeAllowedActionDialog.this.remove();
                    }
                }
            });
            getContentTable().add(button).row();
        }

        button("Back");
    }
}
