package com.greatgame.actions;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.greatgame.application.Mode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Action;
import com.greatgame.environment.Behaviour;
import com.greatgame.fightBehaviourState.FightBehaviourState;

public class AllowedActionDialog extends Dialog {

    public AllowedActionDialog(Behaviour selectedBehaviour, CreatureBehaviour player) {
        super("", Mode.skin);

        text(selectedBehaviour.getType());
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
                        FightBehaviourState state = (FightBehaviourState) player.getState();
                        state.startAction(action, 1000);
                        AllowedActionDialog.this.remove();
                    }
                }
            });
            getContentTable().add(button).row();
        }

        button("Back");
    }
}
