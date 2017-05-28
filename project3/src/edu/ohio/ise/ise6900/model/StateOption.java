package edu.ohio.ise.ise6900.model;

import javafx.scene.paint.Color;

public enum StateOption { BUSY(Color.GREEN), IDLE(Color.YELLOW), DOWN(Color.RED), BLOCKED(Color.BLUE);
	private final Color color;

	StateOption(Color c) {
		color = c;
	}

	public Color getColor () {
		return color;

	}

}
