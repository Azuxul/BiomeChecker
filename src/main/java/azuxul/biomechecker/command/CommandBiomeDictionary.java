package azuxul.biomechecker.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class CommandBiomeDictionary extends CommandBase {

    public String getCommandName(){
    	
    	return "biomedictionary";
    }
    
    public int getRequiredPermissionLevel(){
    	
    	return 2;
    }
    
    public String getCommandUsage(ICommandSender sender){
    	
        return StatCollector.translateToLocal("command.biomedictionary.usage");
    }

    public void processCommand(ICommandSender sender, String[] args){
    	
    	EntityPlayer player = getCommandSenderAsPlayer(sender);
    	
    	if(args.length > 1){
    		
    		String text = "empty";
    		BiomeGenBase biome;
    		
    		try {
    			biome = sender.getEntityWorld().getWorldChunkManager().getBiomeGenAt(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    		}
    		catch(Exception e){
    			throw new WrongUsageException(StatCollector.translateToLocal("command.biomedictionary.usage"), new Object[0]);
    		}
    		
    		Type[] result = BiomeDictionary.getTypesForBiome(biome);
    		if(result != null){
    			
    			if(result.length == 1){
        			
        			text = result[0].name();
        		}
        		else{
        			
            		for(int i = result.length -1; i >= 0; i--){
            			
            			if(text.endsWith("empty"))
            				text = result[i].name();
            			else{
            				if(i < 1){
            					text = text + " " + StatCollector.translateToLocal("text.and") + " " + result[i].name();
            				}
            				else{
            					text = text + ", " + result[i].name();
            				}
            			}
            		}
        		}
    		}
    		if(text.endsWith("empty")){
    			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + StatCollector.translateToLocal("command.biomedictionary.null").replace("%b1", biome.biomeName)));
    		}
    		else{
    			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + StatCollector.translateToLocal("command.biomedictionary.success").replace("%b1", biome.biomeName).replace("%b2", text)));
    		}
    	}
    	else if(args.length == 0){
    		String text = "empty";
    		BiomeGenBase biome;
    		
    		try {
    			biome = sender.getEntityWorld().getWorldChunkManager().getBiomeGenAt(sender.getPlayerCoordinates().posX, sender.getPlayerCoordinates().posZ);
    		}
    		catch(Exception e){
    			throw new WrongUsageException(StatCollector.translateToLocal("command.biomedictionary.usage"), new Object[0]);
    		}
    		
    		Type[] result = BiomeDictionary.getTypesForBiome(biome);
    		if(result != null){
    			
        		if(result.length == 1){
        			
        			text = result[0].name();
        		}
        		else{
        			
            		for(int i = result.length -1; i >= 0; i--){
            			
            			if(text.endsWith("empty"))
            				text = result[i].name();
            			else{
            				if(i < 1){
            					text = text + " " + StatCollector.translateToLocal("text.and") + " " + result[i].name();
            				}
            				else{
            					text = text + ", " + result[i].name();
            				}
            			}
            		}
        		}
    		}
    		if(text.endsWith("empty")){
    			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + StatCollector.translateToLocal("command.biomedictionary.null").replace("%b1", biome.biomeName)));
    		}
    		else{
    			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + StatCollector.translateToLocal("command.biomedictionary.success").replace("%b1", biome.biomeName).replace("%b2", text)));
    		}
    	}
    	else{
    		throw new WrongUsageException(StatCollector.translateToLocal("command.biomedictionary.usage"), new Object[0]);
    	}
    }
	
}
