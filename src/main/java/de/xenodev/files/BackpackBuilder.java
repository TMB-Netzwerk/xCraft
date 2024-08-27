package de.xenodev.files;

public class BackpackBuilder {

    /*

        public static File file;
        public static YamlConfiguration cfg;

        private Player p;

        public BackpackBuilder(Player p){
            this.p = p;
            file = new File("plugins/" + Main.getInstance().getName() + "/backpacks", p.getUniqueId() + ".yml");
            cfg = YamlConfiguration.loadConfiguration(file);
        }

        public void setBuyed(){

        }

        public Boolean getBuyed(){
            return true;
        }

        public void setLevel(Integer level){
            cfg.set(p.getUniqueId() + ".Level", level);
        }

        public Integer getLevel(Player p){
            if(cfg.get("Level") != null) {
                return cfg.getInt("Level");
            }else{
                return 0;
            }
        }

        public Boolean getActive(){
            if(Main.getInstance().getConfig().getBoolean("activate") != false){
                return true;
            }else{
                return false;
            }
        }

        public void save(){
            try { cfg.save(file); } catch (IOException e) { throw new RuntimeException(e); }
        }

        public void setItem(Player p){
            if(getActive() == true) {
                if (p.getInventory().getItem(17) == null || p.getInventory().getItem(17).getItemMeta().getDisplayName().contains("§6Backpack")) {
                    if (getLevel(p) >= 1 && getLevel(p) <= 10) {
                        p.getInventory().setItem(17, new ItemBuilder(Material.PLAYER_HEAD).setOwnerURL("http://textures.minecraft.net/texture/ddaf8edc32afb461aee0713058023101f924e2a7efa883dae72d5d57d4c053d7").setName("§6Backpack §8(§7lv. " + getLevel(p) + "§8)").setLore("§7Slots: §d" + getLevel(p), "§7Kosten: §6" + getCost(getLevel(p)), "§e§oRightclick levelup / Leftclick open", "").build());
                    } else if (getLevel(p) > 10 && getLevel(p) <= 20) {
                        p.getInventory().setItem(17, new ItemBuilder(Material.PLAYER_HEAD).setOwnerURL("http://textures.minecraft.net/texture/cf87525ad84efd16806a26ca01984b280e5ba6403505b6f6c9803c24642abfc7").setName("§6Backpack §8(§7lv. " + getLevel(p) + "§8)").setLore("§7Slots: §d" + getLevel(p), "§7Kosten: §6" + getCost(getLevel(p)), "§e§oRightclick levelup / Leftclick open", "").build());
                    } else if (getLevel(p) > 20 && getLevel(p) <= 29) {
                        p.getInventory().setItem(17, new ItemBuilder(Material.PLAYER_HEAD).setOwnerURL("http://textures.minecraft.net/texture/10d1b0732bf7a70de4dc01559cc5c9811068ef7b6095010382709f94093927f6").setName("§6Backpack §8(§7lv. " + getLevel(p) + "§8)").setLore("§7Slots: §d" + getLevel(p), "§7Kosten: §6" + getCost(getLevel(p)), "§e§oRightclick levelup / Leftclick open", "").build());
                    } else if (getLevel(p) == 30) {
                        p.getInventory().setItem(17, new ItemBuilder(Material.PLAYER_HEAD).setOwnerURL("http://textures.minecraft.net/texture/10d1b0732bf7a70de4dc01559cc5c9811068ef7b6095010382709f94093927f6").setName("§6Backpack §8(§7lv. " + getLevel(p) + "§8)").setLore("§7Slots: §d" + getLevel(p), "§7Kosten: §6Max Upgrade", "§e§oLeftclick open", "").build());
                    } else {
                        p.getInventory().setItem(17, new ItemBuilder(Material.PLAYER_HEAD).setOwnerURL("http://textures.minecraft.net/texture/40b1b53674918391a07a9d00582c058f9280bc526a716c796ee5eab4be10a760").setName("§6Backpack §8(§7Ungekauft§8)").setLore("§7Slots: §d0", "§7Kosten: §6" + getCost(getLevel(p)), "§7§oBackpack kaufen (rightclick)", "").build());
                    }
                } else {
                    p.sendMessage(Main.getPrefix() + "§c§lBitte leere den Slot deines Inventares oben links für den Backpack");
                }
            }else{
                if(p.getInventory().getItem(17).getItemMeta().getDisplayName().equalsIgnoreCase("§6Backpack")){
                    p.getInventory().setItem(17, null);
                }
            }
        }

        public void saveBackpack(Inventory inventory, Player p){
            file2 = new File("plugins/" + Main.getInstance().getName() + "/backpack/saves", p.getUniqueId() + ".yml");
            cfg2 = YamlConfiguration.loadConfiguration(file2);

            ArrayList<ItemStack> saveItems = new ArrayList<>();
            for(ItemStack itemStack : inventory.getContents()) {
                saveItems.add(itemStack);
            }
            cfg2.set("Items", saveItems);
            try { cfg2.save(file2); } catch (IOException e) { throw new RuntimeException(e); }
        }

        public void openBackpack(Player p){
            file2 = new File("plugins/" + Main.getInstance().getName() + "/backpack/saves", p.getUniqueId() + ".yml");
            cfg2 = YamlConfiguration.loadConfiguration(file2);
            if (getLevel(p) >= 1 && getLevel(p) <= 10) {
                Inventory inventory = Bukkit.createInventory(p, 2*9, "§6Backpack");

                if(cfg2.get("Items") != null) {
                    ArrayList<ItemStack> content = (ArrayList<ItemStack>) cfg2.getList("Items");
                    if (content.size() != 0) {
                        ItemStack[] itemStacks = new ItemStack[content.size()];

                        for (int i = 0; i < content.size(); i++) {
                            ItemStack item = content.get(i);
                            if (item != null) {
                                itemStacks[i] = item;
                            } else {
                                itemStacks[i] = null;
                            }
                        }
                        inventory.setContents(itemStacks);
                    }
                }

                for(int i = 0; i < inventory.getSize(); i++){
                    if(inventory.getItem(i) != null) {
                        if (inventory.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§cVerschlossen")) {
                            inventory.setItem(i, null);
                        }
                    }
                }

                for(int i = getLevel(p); i < inventory.getSize(); i++){
                    inventory.setItem(i, new ItemBuilder(Material.BARRIER).setName("§cVerschlossen").build());
                }

                p.openInventory(inventory);
            } else if (getLevel(p) > 10 && getLevel(p) <= 20) {
                Inventory inventory = Bukkit.createInventory(p, 3*9, "§6Backpack");

                if(cfg2.get("Items") != null) {
                    ArrayList<ItemStack> content = (ArrayList<ItemStack>) cfg2.getList("Items");
                    if (content.size() != 0) {
                        ItemStack[] itemStacks = new ItemStack[content.size()];

                        for (int i = 0; i < content.size(); i++) {
                            ItemStack item = content.get(i);
                            if (item != null && item != new ItemStack(Material.BARRIER)) {
                                itemStacks[i] = item;
                            } else {
                                itemStacks[i] = null;
                            }
                        }

                        inventory.setContents(itemStacks);
                    }
                }

                for(int i = 0; i < inventory.getSize(); i++){
                    if(inventory.getItem(i) != null) {
                        if (inventory.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§cVerschlossen")) {
                            inventory.setItem(i, null);
                        }
                    }
                }

                for(int i = getLevel(p); i < inventory.getSize(); i++){
                    inventory.setItem(i, new ItemBuilder(Material.BARRIER).setName("§cVerschlossen").build());
                }

                p.openInventory(inventory);
            } else if (getLevel(p) > 20 && getLevel(p) <= 30) {
                Inventory inventory = Bukkit.createInventory(p, 4*9, "§6Backpack");

                if(cfg2.get("Items") != null) {
                    ArrayList<ItemStack> content = (ArrayList<ItemStack>) cfg2.getList("Items");
                    if (content.size() != 0) {
                        ItemStack[] itemStacks = new ItemStack[content.size()];

                        for (int i = 0; i < content.size(); i++) {
                            ItemStack item = content.get(i);
                            if (item != null && item != new ItemStack(Material.BARRIER)) {
                                itemStacks[i] = item;
                            } else {
                                itemStacks[i] = null;
                            }
                        }

                        inventory.setContents(itemStacks);
                    }
                }

                for(int i = 0; i < inventory.getSize(); i++){
                    if(inventory.getItem(i) != null) {
                        if (inventory.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§cVerschlossen")) {
                            inventory.setItem(i, null);
                        }
                    }
                }

                for (int i = getLevel(p); i < inventory.getSize(); i++) {
                    inventory.setItem(i, new ItemBuilder(Material.BARRIER).setName("§cVerschlossen").build());
                }

                p.openInventory(inventory);
            }else{
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1f, 1f);
                p.sendMessage(Main.getPrefix() + "§7Du hast den Backpack nicht gekauft");
            }
        }

     */

}
