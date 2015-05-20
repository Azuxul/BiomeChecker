package azuxul.biomechecker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import azuxul.biomechecker.command.CommandBiomeDictionary;

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
