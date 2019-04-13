package com.monopoly.window;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.monopoly.card.RedemptionCard;
import com.monopoly.model.Player;

@SuppressWarnings("serial")
public class JailEscapePanel extends JPanel {
	private JLabel lblTurnLeft;
	private JRadioButton rdbRollDouble;
	private JRadioButton rdbPayFee;
	private JRadioButton rdbEscapeCard;
	private ButtonGroup btnGroup;
	private Player player = null;
	
	public JailEscapePanel() {
		setLayout(new GridLayout(2,3));
		lblTurnLeft = new JLabel();
		rdbRollDouble = new JRadioButton("Roll Double");
		rdbPayFee = new JRadioButton("Pay 200K");
		rdbEscapeCard = new JRadioButton("Use Redemption Card");
		add(lblTurnLeft);
		add(new JLabel());
		add(new JLabel());
		
		add(rdbRollDouble);
		add(rdbPayFee);
		add(rdbEscapeCard);
		btnGroup = new ButtonGroup();
		btnGroup.add(rdbRollDouble);
		btnGroup.add(rdbPayFee);
		btnGroup.add(rdbEscapeCard);
	}
	
	public void init(Player player) {
		this.player = player;
		rdbRollDouble.setSelected(true);
		rdbPayFee.setSelected(false);
		rdbEscapeCard.setSelected(false);
		
		lblTurnLeft.setText("Turn Left : " + player.getJailDuration());
		
		if (player.getMoney() >= 200000) {
			rdbPayFee.setEnabled(true);
		} else {
			rdbPayFee.setEnabled(false);
		}
		
		if (player.getCard() instanceof RedemptionCard) {
			rdbEscapeCard.setEnabled(true);
		} else {
			rdbEscapeCard.setEnabled(false);
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean isOptionSelected() {
		if (rdbRollDouble.isSelected() || rdbPayFee.isSelected() || rdbEscapeCard.isSelected()) {
			return true;
		}
		return false;
	}
	
	public boolean isRollDoubleSelected() {
		return rdbRollDouble.isSelected();
	}
	
	public boolean isPayFeeSelected() {
		return rdbPayFee.isSelected();
	}
	
	public boolean isEscapeCardSelected() {
		return rdbEscapeCard.isSelected();
	}
}
