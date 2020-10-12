package icbm.classic.prefab.inventory;

import icbm.classic.ICBMClassic;
import icbm.classic.lib.NBTConstants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.*;

/**
 * Simple inventory implementation
 * Created by robert on 5/1/2015.
 */
public class BasicInventory implements IInventory, Iterable<Map.Entry<Integer, ItemStack>> {

    /** Default slot max count */
    protected int slots;

    /**
     * How much to shift the start of the inventory map,
     * used to adjust save/load process accordingly
     */
    protected int shiftSlotStart = 0;

    protected boolean recalculateFillStatus = true;
    protected boolean isFull = false;

    /** Map of the inventory */
    protected HashMap<Integer, ItemStack> inventoryMap = new HashMap();

    public String inventoryName = "container.inventory.basic";

    protected boolean _loading = false;

    public BasicInventory(int slots) {
        this.slots = slots;
    }

    @Override
    public int getSizeInventory() {
        return slots;
    }

    public Collection<ItemStack> getContainedItems() {
        return this.inventoryMap.values();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {

        if (slot >= 0 && slot < getSizeInventory()) {
            return this.inventoryMap.containsKey(slot) ? this.inventoryMap.get(slot) : ItemStack.EMPTY;
        }

        return ItemStack.EMPTY;

    }

    @Override
    public ItemStack decrStackSize(int slot, int ammount) {

        if (this.getStackInSlot(slot) != null) {

            ItemStack var3;

            if (this.getStackInSlot(slot).getCount() <= ammount) {

                var3 = this.getStackInSlot(slot);

                setInventorySlotContents(slot, ItemStack.EMPTY);
                markDirty();

                return var3;

            }
            else {

                var3 = this.getStackInSlot(slot).split(ammount);

                if (this.getStackInSlot(slot).getCount() == 0) {
                    setInventorySlotContents(slot, ItemStack.EMPTY);
                }

                markDirty();
                return var3;

            }

        }
        else {

            return ItemStack.EMPTY;

        }

    }

    @Override
    public ItemStack removeStackFromSlot(int index) {

        ItemStack stack = getStackInSlot(index);

        if (stack != null) {
            setInventorySlotContents(0, ItemStack.EMPTY);
            return stack;
        }

        return ItemStack.EMPTY;

    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack insertStack) {

        if (slot >= 0 && slot < getSizeInventory()) {

            ItemStack pre_stack = getStackInSlot(slot) != null ? getStackInSlot(slot).copy() : null;

            if (insertStack != ItemStack.EMPTY) {
                inventoryMap.put(slot, insertStack);
            }
            else if (inventoryMap.containsKey(slot)) {
                inventoryMap.remove(slot);
            }
            if (!_loading && !InventoryUtility.stacksMatchExact(pre_stack, getStackInSlot(slot))) {
                recalculateFillStatus = true;
                onInventoryChanged(slot, pre_stack, getStackInSlot(slot));
            }

        }
        else {

            ICBMClassic.logger().error("BasicInventory: something tried to set " + insertStack + " into slot " + slot + " which is outside the 0 - " + (getSizeInventory() - 1) + " limit");

        }

    }

    /**
     * Called when the stack in the inventory slot has changed
     *
     * @param slot
     * @param prev
     * @param item
     */
    protected void onInventoryChanged(int slot, ItemStack prev, ItemStack item) {
        markDirty();
    }

    @Override
    public String getName() {
        return inventoryName;
    }

    public BasicInventory setInventoryName(String name) {
        this.inventoryName = name;
        return this;
    }

    @Override
    public void openInventory(PlayerEntity player) {

    }

    @Override
    public void closeInventory(PlayerEntity player) {

    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getName());
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i >= this.getSizeInventory() && i < getSizeInventory();
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity par1EntityPlayer) {
        return true;
    }

    public void load(CompoundNBT nbt) {

        _loading = true;
        this.inventoryMap.clear();

        ListNBT nbtList = nbt.getList(NBTConstants.ITEMS, 10);

        for (int i = 0; i < nbtList.size(); ++i) {

            CompoundNBT stackTag = nbtList.getCompound(i);
            byte id = stackTag.getByte(NBTConstants.SLOT);

            if (id >= 0 && id < this.getSizeInventory())
                this.setInventorySlotContents(id, ItemStack.read(stackTag));

        }

        nbt.put(NBTConstants.ITEMS, nbtList);
        _loading = false;

    }

    public CompoundNBT save(CompoundNBT nbt) {

        ListNBT nbtList = new ListNBT();

        for (int i = shiftSlotStart; i < this.getSizeInventory() + shiftSlotStart; ++i) {
            if (!this.getStackInSlot(i + shiftSlotStart).isEmpty()) {
                CompoundNBT var4 = new CompoundNBT();
                var4.putByte(NBTConstants.SLOT, (byte) i);
                this.getStackInSlot(i + shiftSlotStart).write(var4);
                nbtList.add(var4);
            }
        }

        nbt.put(NBTConstants.ITEMS, nbtList);

        return nbt;

    }

    /**
     * Called to see if the inventory is empty
     *
     * @return true if nothing is contained
     */
    public boolean isEmpty() {
        return inventoryMap.isEmpty();
    }

    public boolean isFull() {

        if (recalculateFillStatus) {

            recalculateFillStatus = false;

            for (int i = 0; i < getSizeInventory(); i++) {
                if (roomLeftInSlot(i) > 0) {
                    isFull = false;
                    return false;
                }
            }

            isFull = true;

        }

        return isFull;

    }

    public ArrayList<Integer> getFilledSlots() {

        ArrayList<Integer> slots = new ArrayList();

        for (int slot = 0; slot < getSizeInventory(); slot++)
            if (!getStackInSlot(slot).isEmpty())
                slots.add(slot);

        return slots;

    }

    public ArrayList<Integer> getEmptySlots() {

        ArrayList<Integer> slots = new ArrayList();

        for (int slot = 0; slot < getSizeInventory(); slot++)
            if (getStackInSlot(slot).isEmpty())
                slots.add(slot);

        return slots;

    }

    public ArrayList<Integer> getSlotsWithSpace() {

        ArrayList<Integer> slots = new ArrayList();

        for (int slot = 0; slot < getSizeInventory(); slot++)
            if (roomLeftInSlot(slot) > 0)
                slots.add(slot);

        return slots;

    }

    public int roomLeftInSlot(int slot) {

        if (!getStackInSlot(slot).isEmpty()) {
            int maxSpace = Math.min(getStackInSlot(slot).getMaxStackSize(), getInventoryStackLimit());
            return maxSpace - getStackInSlot(slot).getCount();
        }

        return getInventoryStackLimit();

    }

    @Override
    public Iterator<Map.Entry<Integer, ItemStack>> iterator() {
        return inventoryMap.entrySet().iterator();
    }

    @Override
    public String toString() {
        return "BasicInventory[" + getName() + ", " + getSizeInventory() + "]@" + hashCode();
    }

    @Override
    public boolean equals(Object object) {

        if (object == this)
            return true;
        else if (object instanceof BasicInventory)
            return ((BasicInventory) object).slots == slots && ((BasicInventory) object).inventoryMap == inventoryMap;

        return false;

    }

    @Override
    public int hashCode() {
        return inventoryMap != null ? inventoryMap.hashCode() : super.hashCode();
    }
}
