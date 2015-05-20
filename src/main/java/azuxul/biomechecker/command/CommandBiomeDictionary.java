package azuxul.biomechecker.command;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
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

    public String getName(){
    	
    	return "biomedictionary";
    }
    
    public int getRequiredPermissionLevel(){
    	
    	return 2;
    }
    
    public String getCommandUsage(ICommandSender sender){
    	
        return StatCollector.translateToLocal("command.biomedictionary.usage");
    }

    public void execute(ICommandSender sender, String[] args) throws CommandException{
    	
    	EntityPlayer player = getCommandSenderAsPlayer(sender);
    	
    	if(args.length > 1){
    		
    		String text = "empty";
    		BiomeGenBase[] biome;
    		
    		try {
    			biome = sender.getEntityWorld().getWorldChunkManager().getBiomeGenAt(null, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[0]), Integer.parseInt(args[1]), true);
    		}
    		catch(Exception e){
    			throw new WrongUsageException(StatCollector.translateToLocal("command.biomedictionary.usage"), new Object[0]);
    		}
    		
    		Type[] result = BiomeDictionary.getTypesForBiome(biome[0]);
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
    			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + StatCollector.translateToLocal("command.biomedictionary.null").replace("%b1", biome[0].biomeName)));
    		}
    		else{
    			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + StatCollector.translateToLocal("command.biomedictionary.success").replace("%b1", biome[0].biomeName).replace("%b2", text)));
    		}
    	}
    	else if(args.length == 0){
    		String text = "empty";
    		BiomeGenBase[] biome;
    		
    		try {
    			biome = sender.getEntityWorld().getWorldChunkManager().getBiomeGenAt(null, sender.getPosition().getX(), sender.getPosition().getZ(), sender.getPosition().getX(), sender.getPosition().getZ(), false);
    		}
    		catch(Exception e){
    			throw new WrongUsageException(StatCollector.translateToLocal("command.biomedictionary.usage"), new Object[0]);
    		}
    		
    		Type[] result = BiomeDictionary.getTypesForBiome(biome[0]);
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
    			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + StatCollector.translateToLocal("command.biomedictionary.null").replace("%b1", biome[0].biomeName)));
    		}
    		else{
    			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + StatCollector.translateToLocal("command.biomedictionary.success").replace("%b1", biome[0].biomeName).replace("%b2", text)));
    		}
    	}
    	else{
    		throw new WrongUsageException(StatCollector.translateToLocal("command.biomedictionary.usage"), new Object[0]);
    	}
    }
    
    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos){
    	
    	String s = "";
    	
    	if(args.length == 1){
    		s = Integer.toString(pos.getX());
    	}
    	else if(args.length == 2){
    		s = Integer.toString(pos.getZ());
    	}
    	
    	return Lists.newArrayList(new String[] {s});
    }
	
}
