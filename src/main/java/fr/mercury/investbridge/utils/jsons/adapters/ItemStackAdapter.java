package fr.mercury.investbridge.utils.jsons.adapters;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.mercury.investbridge.MercuryInvestBridge;
import fr.mercury.investbridge.utils.jsons.GsonAdapter;
import fr.mercury.investbridge.utils.jsons.ItemStackUtils;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class ItemStackAdapter extends GsonAdapter<ItemStack> {

    public ItemStackAdapter(MercuryInvestBridge plugin) {
        super(plugin);
    }

    @Override
    public void write(JsonWriter writer, ItemStack value) throws IOException {
        writer.value(ItemStackUtils.serializeItemStack(value));
    }

    @Override
    public ItemStack read(JsonReader reader) throws IOException {
        return ItemStackUtils.deserializeItemStack(reader.nextString());
    }
}
