package org.jglrxavpok.mods.decraft.client.config;

import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.ArrayEntry;
import net.minecraftforge.fml.client.config.GuiConfigEntries.ButtonEntry;
import net.minecraftforge.fml.client.config.IConfigElement;


public class ModGuiConfigEntries 
{
	
	public static class ExcludedItemsArrayEntry extends ArrayEntry 
	{

		public ExcludedItemsArrayEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) 
		{
			super(owningScreen, owningEntryList, configElement);
		}
		
		@Override
		public void updateValueButtonText()
		{
			this.btnValue.displayString = currentValues.length + " item(s)";
		}
		
	}
	
	
	public static class UncraftingMethodCycleEntry extends ButtonEntry
	{
		
		protected final int beforeIndex;
		protected final int defaultIndex;
		protected int currentIndex;

		public UncraftingMethodCycleEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement)
		{
			super(owningScreen, owningEntryList, configElement);
			beforeIndex = Integer.valueOf((String)configElement.get());
			defaultIndex = Integer.valueOf((String)configElement.getDefault());
			currentIndex = beforeIndex;
			this.btnValue.enabled = enabled();
			updateValueButtonText();
		}


		@Override
		public void updateValueButtonText()
		{
			this.btnValue.displayString = configElement.getValidValues()[currentIndex];
		}

		@Override
		public void valueButtonPressed(int slotIndex)
		{
			if (enabled())
			{
				if (++this.currentIndex >= configElement.getValidValues().length) this.currentIndex = 0;
				updateValueButtonText();
			}
		}

		@Override
		public boolean isDefault()
		{
			return currentIndex == defaultIndex;
		}

		@Override
		public void setToDefault()
		{
			if (enabled())
			{
				currentIndex = defaultIndex;
				updateValueButtonText();
			}
		}

		@Override
		public boolean isChanged()
		{
			return currentIndex != beforeIndex;
		}

		@Override
		public void undoChanges()
		{
			if (enabled())
			{
				currentIndex = beforeIndex;
				updateValueButtonText();
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean saveConfigElement()
		{
			if (enabled() && isChanged())
			{
				configElement.set(currentIndex);
				return configElement.requiresMcRestart();
			}
			return false;
		}

		@Override
		public String getCurrentValue()
		{
			return configElement.get().toString();
		}

		@Override
		public String[] getCurrentValues()
		{
			return new String[] { getCurrentValue() };
		}
	}

}
