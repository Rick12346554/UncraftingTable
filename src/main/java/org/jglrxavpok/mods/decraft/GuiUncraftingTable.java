package org.jglrxavpok.mods.decraft;

import java.awt.Color;

import org.jglrxavpok.mods.decraft.ContainerUncraftingTable.UncraftingStatus;
import org.jglrxavpok.mods.decraft.common.config.ModConfiguration;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GuiUncraftingTable extends GuiContainer
{

    public ContainerUncraftingTable container;
    private String blockName;
    private boolean inverted;
    private World worldObj;
    private EntityPlayer player;

    public GuiUncraftingTable(InventoryPlayer playerInventory, World world, String blockName, boolean inverted)
    {
    	super(new ContainerUncraftingTable(playerInventory, world, inverted));
    	
        container = (ContainerUncraftingTable)inventorySlots;
        this.blockName = blockName;
        this.inverted = inverted;
        this.worldObj = world;
        this.player = playerInventory.player;
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        int xSize = this.xSize;
        int ySize = this.ySize;
        
        if (!inverted)
        {
        	// fontRendererObj.drawString:
        	// Args: string, x, y, color, dropShadow
        	
        	
        	// render the block name at the top of the gui
            fontRendererObj.drawString(blockName, xSize / 2 - fontRendererObj.getStringWidth(blockName) / 2 + 1, 5, 4210752);
            
            // write "inventory" above the player inventory
            fontRendererObj.drawString(I18n.format("container.inventory"), 6, ySize - 96 + 2, 4210752);

            // write "compute:" above the input slots
            String compute = I18n.format("uncrafting.compute") + ":";
            fontRendererObj.drawString(TextFormatting.DARK_GRAY + compute + TextFormatting.RESET, 24 - fontRendererObj.getStringWidth(compute) / 2 + 1, 22, 0);
            fontRendererObj.drawString(TextFormatting.GRAY + compute + TextFormatting.RESET, 24 - fontRendererObj.getStringWidth(compute) / 2, 21, 0);
            
            // write the xp cost above the arrow
            Color darkGreen = new Color(75, 245, 75);
            fontRendererObj.drawString(TextFormatting.DARK_GRAY + "" + TextFormatting.UNDERLINE + "" + (ModConfiguration.standardLevel + container.uncraftingCost) + " levels" + TextFormatting.RESET, xSize / 2 - fontRendererObj.getStringWidth((ModConfiguration.standardLevel + container.uncraftingCost) + " levels") / 2 + 1, ySize - 126 - 10, 0);
            fontRendererObj.drawString(TextFormatting.UNDERLINE + "" + (ModConfiguration.standardLevel + container.uncraftingCost) + " levels" + TextFormatting.RESET, xSize / 2 - fontRendererObj.getStringWidth((ModConfiguration.standardLevel + container.uncraftingCost) + " levels") / 2, ySize - 127 - 10, darkGreen.getRGB());


//            // draw the arrow with the cross through it
//            GL11.glPushMatrix();
//            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//            this.mc.renderEngine.bindTexture(new ResourceLocation("uncraftingTable:textures/gui/container/uncrafting_gui.png"));
//            this.drawTexturedModalRect(75, 32, 176, 0, 28, 21);
//            GL11.glPopMatrix();
            
            
            String string = container.uncraftingStatusText;
            if (string != null)
            {
                UncraftingStatus msgType = container.uncraftingStatus;
                TextFormatting format = TextFormatting.GREEN;
                TextFormatting shadowFormat = TextFormatting.DARK_GRAY;
                if (msgType == ContainerUncraftingTable.UncraftingStatus.ERROR)
                {
                    format = TextFormatting.WHITE;
                    shadowFormat = TextFormatting.DARK_RED;
                }
                fontRendererObj.drawString(shadowFormat + string + TextFormatting.RESET, 6 + 1, ySize - 95 + 2 - fontRendererObj.FONT_HEIGHT, 0);
                fontRendererObj.drawString(format + string + TextFormatting.RESET, 6, ySize - 96 + 2 - fontRendererObj.FONT_HEIGHT, 0);
            }
        }
        else
        {
            int height = 166 - 8;
            
            fontRendererObj.drawString(blockName, xSize / 2 - fontRendererObj.getStringWidth(blockName) / 2 + 1, height - 5, 4210752);
            fontRendererObj.drawString(I18n.format("container.inventory"), 6, height - ySize - 96 + 2, 4210752);

            Color darkGreen = new Color(75, 245, 75);
            String string1 = "Calculs:";
            
            fontRendererObj.drawString(TextFormatting.DARK_GRAY + string1 + TextFormatting.RESET, 24 - fontRendererObj.getStringWidth(string1) / 2 + 1, height - 22, 0);
            fontRendererObj.drawString(TextFormatting.GRAY + string1 + TextFormatting.RESET, 24 - fontRendererObj.getStringWidth(string1) / 2, height - 21, 0);
            fontRendererObj.drawString(TextFormatting.DARK_GRAY + "" + TextFormatting.UNDERLINE + "" + (ModConfiguration.standardLevel + container.uncraftingCost) + " levels" + TextFormatting.RESET, xSize / 2 - fontRendererObj.getStringWidth((ModConfiguration.standardLevel + container.uncraftingCost) + " levels") / 2 + 1, height - (ySize - 126 - 10), 0);
            fontRendererObj.drawString(TextFormatting.UNDERLINE + "" + (ModConfiguration.standardLevel + container.uncraftingCost) + " levels" + TextFormatting.RESET, xSize / 2 - fontRendererObj.getStringWidth((ModConfiguration.standardLevel + container.uncraftingCost) + " levels") / 2, height - (ySize - 127 - 10), darkGreen.getRGB());

            String string = container.uncraftingStatusText;
            if (string != null)
            {
                UncraftingStatus msgType = container.uncraftingStatus;
                TextFormatting format = TextFormatting.GREEN;
                TextFormatting shadowFormat = TextFormatting.DARK_GRAY;
                if (msgType == ContainerUncraftingTable.UncraftingStatus.ERROR)
                {
                    format = TextFormatting.WHITE;
                    shadowFormat = TextFormatting.DARK_RED;
                }
                fontRendererObj.drawString(shadowFormat + string + TextFormatting.RESET, 6 + 1, height - (ySize - 95 + 2 - fontRendererObj.FONT_HEIGHT), 0);
                fontRendererObj.drawString(format + string + TextFormatting.RESET, 6, height - (ySize - 96 + 2 - fontRendererObj.FONT_HEIGHT), 0);
            }
        }
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        if (this.inverted) {
            this.mc.renderEngine.bindTexture(new ResourceLocation(ModUncrafting.MODID + ":textures/gui/container/uncrafting_gui_redstoned.png"));
        } else {
            this.mc.renderEngine.bindTexture(new ResourceLocation(ModUncrafting.MODID + ":textures/gui/container/uncrafting_gui.png"));
        }
        int k = this.width / 2 - this.xSize / 2;
        int l = this.height / 2 - this.ySize / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        GL11.glPopMatrix();
    }

}
