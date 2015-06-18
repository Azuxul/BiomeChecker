package azuxul.biomechecker.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class CommandBiomeDictionary extends CommandBase {

    @Override
	public String getName(){
    	
    	return "biomedictionary";
    }
    
    @Override
	public int getRequiredPermissionLevel(){
    	
    	return 3;
    }
    
    @Override
	public String getCommandUsage(ICommandSender sender){
    	
        return StatCollector.translateToLocal("command.biomedictionary.usage");
    }

    @Override
	public void execute(ICommandSender sender, String[] args) throws CommandException{
    	
    	EntityPlayer player = null;
    	
    	player = CommandBase.getCommandSenderAsPlayer(sender);
    	
    	if(args.length > 1){
    		
    		String text = "empty";
    		BiomeGenBase[] biomeTmp = null;
    		BiomeGenBase biome;
    		
    		try {
    			biomeTmp = sender.getEntityWorld().getWorldChunkManager().getBiomeGenAt(biomeTmp, Integer.parseInt(args[0]), Integer.parseInt(args[1]), 1, 1, true);
    			biome = biomeTmp[0];
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
    			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + StatCollector.translateToLocal("command.biomedictionary.null").replace("%b1", biome.biomeName)));
    		}
    		else{
    			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + StatCollector.translateToLocal("command.biomedictionary.success").replace("%b1", biome.biomeName).replace("%b2", text)));
    		}
    	}
    	
    	else if(args.length == 0 && player != null){
    		String text = "empty";
    		BiomeGenBase[] biomeTmp = null;
    		BiomeGenBase biome;
    		
    		try {
    			biomeTmp = sender.getEntityWorld().getWorldChunkManager().getBiomeGenAt(biomeTmp, sender.getPosition().getX(), sender.getPosition().getZ(), 1, 1, true);
    			biome = biomeTmp[0];
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
