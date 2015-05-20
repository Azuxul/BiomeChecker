package azuxul.biomechecker;

import azuxul.biomechecker.command.CommandBiomeDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Main.MODID, version = Main.VERSION, name = Main.NAME)
public class Main {

	public static final String MODID = "biomechecker";
	public static final String VERSION = "1.0";
	public static final String NAME = "BiomeChecker";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
		//Check updates whit VersionChecker
		FMLInterModComms.sendRuntimeMessage(Main.MODID, "VersionChecker", "addVersionCheck", "https://raw.githubusercontent.com/Azuxul/BiomeChecker/master/version.json");
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event){
		
		event.registerServerCommand(new CommandBiomeDictionary());
	}
}
