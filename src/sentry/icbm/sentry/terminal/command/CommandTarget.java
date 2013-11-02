package icbm.sentry.terminal.command;

import icbm.sentry.ISpecialAccess;
import icbm.sentry.access.AccessLevel;
import icbm.sentry.platform.TileEntityTurretPlatform;
import icbm.sentry.terminal.ITerminal;
import icbm.sentry.terminal.TerminalCommand;
import icbm.sentry.turret.sentries.TileEntityAutoTurret;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

public class CommandTarget extends TerminalCommand
{
	@Override
	public String getCommandPrefix()
	{
		return "target";
	}

	@Override
	public boolean processCommand(EntityPlayer player, ITerminal terminal, String[] args)
	{
		if (terminal instanceof TileEntityTurretPlatform)
		{
			TileEntityTurretPlatform turret = (TileEntityTurretPlatform) terminal;

			if (turret.getTurret() instanceof TileEntityAutoTurret)
			{
				TileEntityAutoTurret sentry = ((TileEntityAutoTurret) turret.getTurret());

				if (args.length > 1)
				{
					String obj = args[1];
					String bool = "";
					boolean change = false;

					if (args.length > 2)
					{
						bool = args[2];
						change = Boolean.getBoolean(bool);
					}

					if (obj.equalsIgnoreCase("player"))
					{
						if (!bool.isEmpty())
						{
							sentry.targetPlayers = change;
						}
						else
						{
							sentry.targetPlayers = !sentry.targetPlayers;
						}

						return true;
					}
					else if (obj.equalsIgnoreCase("hostile"))
					{
						if (!bool.isEmpty())
						{
							sentry.targetHostile = change;
						}
						else
						{
							sentry.targetHostile = !sentry.targetHostile;
						}

						return true;
					}
					else if (obj.equalsIgnoreCase("friendly"))
					{
						if (!bool.isEmpty())
						{
							sentry.targetFriendly = change;
						}
						else
						{
							sentry.targetFriendly = !sentry.targetFriendly;
						}

						return true;
					}
					else if (obj.equalsIgnoreCase("air"))
					{
						if (!bool.isEmpty())
						{
							sentry.targetAir = change;
						}
						else
						{
							sentry.targetAir = !sentry.targetAir;
						}

						return true;
					}
				}

				terminal.addToConsole("[player|hostile|friendly|air] [true|false]");
				return false;
			}
		}

		return false;
	}

	@Override
	public boolean canPlayerUse(EntityPlayer var1, ISpecialAccess mm)
	{
		return mm.getUserAccess(var1.username).ordinal() >= AccessLevel.ADMIN.ordinal() || var1.capabilities.isCreativeMode;
	}

	@Override
	public boolean showOnHelp(EntityPlayer player, ISpecialAccess mm)
	{
		return this.canPlayerUse(player, mm);
	}

	@Override
	public List<String> getCmdUses(EntityPlayer player, ISpecialAccess mm)
	{
		List<String> cmds = new ArrayList<String>();
		cmds.add("target <obj> [bool]");
		return cmds;
	}

	@Override
	public boolean canMachineUse(ISpecialAccess specialAccess)
	{
		if (specialAccess instanceof TileEntityTurretPlatform)
		{
			return ((TileEntityTurretPlatform) specialAccess).getTurret() instanceof TileEntityAutoTurret;
		}
		return false;
	}

}
