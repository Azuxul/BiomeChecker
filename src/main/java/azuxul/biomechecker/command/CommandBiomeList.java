package azuxul.biomechecker.command;

import java.io.PrintStream;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class CommandBiomeList extends CommandBase {
	
	protected String fileName = "biomeList.txt";
	
	@Override
	public String getCommandName(){
		
		return "biomelist";
	}
	
    @Override
	public int getRequiredPermissionLevel(){
    	
    	return 2;
    }
    
    @Override
	public String getCommandUsage(ICommandSender sender){
    	
        return StatCollector.translateToLocal("command.biomelist.usage");
    }
    
    @Override
	public void processCommand(ICommandSender sender, String[] args){
    	
    	try {
    		
    		EntityPlayer player = getCommandSenderAsPlayer(sender);
    		PrintStream out = new PrintStream(fileName);
			BiomeGenBase[] result = BiomeGenBase.getBiomeGenArray();
			
			String text = "empty";
			
			out.println("id | name | temperature | biome type (ForgeBiomeDictionary)");
			out.println("--------------------------------------------------------------------------");
			
			for(int i = 0; i <= result.length - 1; i++){
				if(result[i] != null){
					
					Type[] biomeDic = BiomeDictionary.getTypesForBiome(result[i]);
		    		if(result != null){
		    			
		        		if(biomeDic.length == 1){
		        			
		        			text = biomeDic[0].name();
		        		}
		        		else{
		        			
		            		for(int b = biomeDic.length -1; b >= 0; b--){
		            			
		            			if(text.endsWith("empty"))
		            				text = biomeDic[b].name();
		            			else{
		            				if(b < 1){
		            					text = text + " " + StatCollector.translateToLocal("text.and") + " " + biomeDic[b].name();
		            				}
		            				else{
		            					text = text + ", " + biomeDic[b].name();
		            				}
		            			}
		            		}
		        		}
		        		
		    		}
		    		
		    		out.println(result[i].biomeID + " - " + result[i].biomeName + " - " + result[i].temperature + " - " + text);
		    		out.println("--------------------------------------------------------------------------");
		    		
		    		text = "empty";
				}
			}
			out.close();
			
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + StatCollector.translateToLocal("command.biomelist.success").replace("%b1", fileName)));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new WrongUsageException(StatCollector.translateToLocal("command.biomelist.error"), new Object[0]);
		}
    }

}
