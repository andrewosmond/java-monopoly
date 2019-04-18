package com.monopoly.window;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreatePlayerPanel extends JPanel implements ItemListener{
	private Integer[] arrNumOfPlayer = { 2, 3, 4 };
	private String[] arrCharacterName = { "Claire", "Harold", "Barton", "Michael" };
	private JComboBox<Integer> cmbNumOfPlayer = null;
	private JComboBox<String>[] cmbCharacter = null;

	private JLabel lblNumOfPlayer = new JLabel("Num of Player");
	private JLabel lblPlayer = new JLabel("Player");
	private JLabel lblName = new JLabel("Name");
	private JLabel lblCharacter = new JLabel("Character");
	private JLabel lblPlayer1 = new JLabel("Player1");
	private JLabel lblPlayer2 = new JLabel("Player2");
	private JLabel lblPlayer3 = new JLabel("Player3");
	private JLabel lblPlayer4 = new JLabel("Player4");

	private JTextField[] txtName = new JTextField[4];

	@SuppressWarnings("unchecked")
	public CreatePlayerPanel() {
		setLayout(new GridLayout(7, 3));

		cmbNumOfPlayer = new JComboBox<>(arrNumOfPlayer);
		cmbCharacter = new JComboBox[4];
		for (int i = 0; i < 4; i++) {
			cmbCharacter[i] = new JComboBox<>(arrCharacterName);
			txtName[i] = new JTextField();
		}

		add(lblNumOfPlayer);
		add(cmbNumOfPlayer);
		add(new JLabel());

		add(lblPlayer);
		add(lblName);
		add(lblCharacter);

		add(lblPlayer1);
		add(txtName[0]);
		add(cmbCharacter[0]);

		add(lblPlayer2);
		add(txtName[1]);
		add(cmbCharacter[1]);

		add(lblPlayer3);
		add(txtName[2]);
		add(cmbCharacter[2]);

		add(lblPlayer4);
		add(txtName[3]);
		add(cmbCharacter[3]);

		txtName[2].setEnabled(false);
		cmbCharacter[2].setEnabled(false);
		txtName[3].setEnabled(false);
		cmbCharacter[3].setEnabled(false);

		cmbNumOfPlayer.addItemListener(this);
	}

	public int getNumOfPlayer() {
		return Integer.parseInt(cmbNumOfPlayer.getSelectedItem().toString());
	}
	
	public boolean isNameEmpty() {
		for (int i = 0; i < getNumOfPlayer(); i++) {
			if (txtName[i].getText().toString().equals(""))
				return true;
		}
		return false;
	}

	public Vector<String> getPlayerNames() {
		Vector<String> playerNames = new Vector<String>();
		for (int i = 0; i < getNumOfPlayer(); i++) {	
			playerNames.add(txtName[i].getText().toString());
		}
		return playerNames;
	}

	public Vector<String> getCharacterNames() {
		Vector<String> characters = new Vector<String>();
		for (int i = 0; i < getNumOfPlayer(); i++) {
			characters.add(cmbCharacter[i].getSelectedItem().toString());
		}
		return characters;
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbNumOfPlayer) {
			for (int i = 0; i < 4; i++) {
				if (i > getNumOfPlayer() - 1) {
					txtName[i].setText("");
					cmbCharacter[i].setSelectedIndex(0);
					txtName[i].setEnabled(false);
					cmbCharacter[i].setEnabled(false);
				} else {
					txtName[i].setEnabled(true);
					cmbCharacter[i].setEnabled(true);
				}
			}
		}
	}
}
