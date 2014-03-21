package icbm.sentry.platform.gui.user;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import calclavia.lib.access.AccessUser;
import calclavia.lib.prefab.terminal.TileTerminal;

/** @author DarkGuardsman */
public class GuiEditUser extends GuiAccessGuiComponent
{
    private AccessUser user;

    private GuiButton remove_button;
    
    public GuiEditUser(GuiUserAccess return_gui, AccessUser user)
    {
        super(return_gui);
        this.user = user;
    }
    
    @Override
    public void initGui()
    {
        super.initGui();

        remove_button = new GuiButton(1, this.guiLeft + 95, this.guiTop + 150, 50, 20, "REMOVE");
        this.buttonList.add(remove_button);

    }
    
    @Override
    protected void actionPerformed(GuiButton button)
    {
        super.actionPerformed(button);
        if (button == remove_button)
        {
            if (return_gui.tileEntity instanceof TileTerminal)
            {
                String command = "access user remove " + user.getName();
                ((TileTerminal) return_gui.tileEntity).sendCommandToServer(return_gui.player, command);
                FMLCommonHandler.instance().showGuiScreen(return_gui);
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        super.drawGuiContainerForegroundLayer(x, y);
        this.fontRenderer.drawString("\u00a77" + (user != null ? user.getName() : "Error"), (int) (this.containerWidth / 2 - 7 * 2.5), 4, 4210752);
    }
}
