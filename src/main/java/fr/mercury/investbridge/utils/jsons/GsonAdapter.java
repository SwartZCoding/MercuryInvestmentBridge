package fr.mercury.investbridge.utils.jsons;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import fr.mercury.investbridge.MercuryInvestBridge;

public abstract class GsonAdapter<T> extends TypeAdapter<T> {
	
  private MercuryInvestBridge plugin;
  
  public GsonAdapter(MercuryInvestBridge plugin) {
    this.plugin = plugin;
  }
  
  public Gson getGson() {
    return this.plugin.getGson();
  }
}
