package com.amelialotus.blockstatetraining.config;

/*
 * User: TW
 * Date: 2/4/2015
 *
 * The Startup classes for this example are called during startup, in the following order:
 *  preInitCommon
 *  preInitClientOnly
 *  initCommon
 *  initClientOnly
 *  postInitCommon
 *  postInitClientOnly
 *  See MinecraftByExample class for more information
 */
public class StartupCommon
{
	public static void preInitCommon()
	{
    configInit.preInit();
   // System.out.println("Config: myInteger=" + configInit.myInteger
    System.out.println("Config: myBoolean=" + configInit.myBoolean);
    //                           + "; myString=" + configInit.myString);
   // System.out.println("Config: myDouble=" + configInit.myDouble
   //                            + "; myColour=" + configInit.myColour);
    System.out.print("Config: myIntList=");
    //for (int value : configInit.myIntList) {
    //  System.out.print(value + "; ");
   // }
    System.out.println();

  }

	public static void initCommon()
	{
	}

	public static void postInitCommon()
	{
	}

}