package com.jdawg3636.icbm.common.item;

import com.jdawg3636.icbm.common.entity.EntityMissile;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ItemMissile extends Item {

    private RegistryObject<EntityType<EntityMissile>> missileEntity;

    public ItemMissile(Item.Properties properties, RegistryObject<EntityType<EntityMissile>> missileEntity) {
        super(properties);
        this.missileEntity = missileEntity;
    }

    public RegistryObject<EntityType<EntityMissile>> getMissileEntity() {
        return missileEntity;
    }

}
