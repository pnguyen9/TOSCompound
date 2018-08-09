package view;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import model.Arte;
import model.Character;
import model.Compound;

@SuppressWarnings("serial")
public class NameListCellRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		if (value instanceof Arte) {
			value = ((Arte) value).getName();
		}

		if (value instanceof Character) {
			value = ((Character) value).getName();
		}

		if (value instanceof Compound) {
			Compound compound = (Compound) value;
			Arte compoundArte = compound.getCompoundArte();
			value = compoundArte.getName();
		}

		return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}

}
