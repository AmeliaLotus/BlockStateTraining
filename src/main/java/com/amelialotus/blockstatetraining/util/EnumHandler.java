package com.amelialotus.blockstatetraining.util;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {
	
	public static enum woolTypes implements IStringSerializable {
		WHITE("white", 0),
		ORANGE("orange", 1),
		MAGENTA("magenta", 2),
		LIGHTBLUE("lightblue", 3),
		YELLOW("yellow", 4),
		LIME("lime", 5),
		PINK("pink", 6),
		GRAY("gray", 7),
		LIGHTGRAY("lightgray", 8),
		CYAN("cyan", 9),
		PURPLE("purple", 10),
		BLUE("blue", 11),
		BROWN("brown", 12),
		GREEN("green", 13),
		RED("red", 14),
		BLACK("black", 15);

		private int ID;
		private String name;
		
		private woolTypes(String name, int ID) {
			this.ID = ID;
			this.name = name;
		}
		
		@Override
		public String getName() {
			return this.name;
		}
		
		public int getID() {
			return ID;
		}
		
		public String toString() {
			return getName();
		}
		
	}

}
