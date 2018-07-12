package com.amelialotus.blockstatetraining.config;

public class StartupClientOnly 
	{	
		public static void preInitClientOnly()
		{
			configInit.clientPreInit();  // used to set up an event handler for the GUI so that the altered values are
	                                    //  saved back to disk.
		}
	
		public static void initClientOnly() 
		{
			//things go here if necessary
		}
	
		public static void postInitClientOnly()
		{
			//things go here if necessary
		}
	
}