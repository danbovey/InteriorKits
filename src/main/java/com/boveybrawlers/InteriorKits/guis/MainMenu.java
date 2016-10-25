package com.boveybrawlers.InteriorKits.guis;

import com.boveybrawlers.InteriorKits.InteriorKits;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MainMenu extends GUI {

	public MainMenu(InteriorKits plugin) {
		super(plugin);
		this.setTitle("Menu");
	}

	public void open(Player player, int page) {
		this.inv = Bukkit.createInventory(player, 18, this.getTitle());
		
		this.createItem("Food", "a6ef1c25f516f2e7d6f7667420e33adcf3cdf938cb37f9a41a8b35869f569b");
		this.createItem("Devices","cc99d8fbad11f9b73340bcc1adf6dd28f67f754b52ce4e502b4fe02b16b1834");
		this.createItem("Misc", "49f1f07e2b1c32bb64a128e529f3af1e5286e518544edf8cbaa6c4065b476b");
		this.createItem("Alphabet", "a67d813ae7ffe5be951a4f41f2aa619a5e3894e85ea5d4986f84949c63d7672e");
		this.createItem("Interior", "c2847cd5717e5f5a64e1ba9cb481dc9e22c78ca23f8516d553f55412fa113e0");
		this.createItem("Colour", "36f69f7b7538b41dc3439f3658abbd59facca366f190bcf1d6d0a026c8f96");
		this.createItem("Blocks", "290d4fcb2ce03b94d920f0a9e7a54b32cfc7a1d33a6dfe9757d8678cbb591");
		this.createItem("Mobs", "74e9c6e98582ffd8ff8feb3322cd1849c43fb16b158abb11ca7b42eda7743eb");
		this.createItem("Games", "5d86e7bd28c146f71514c782cac055860d1f372b4a9be3fe65cfe1104733ba");
		this.createItem("Characters", "6f7eb75e5542cc4937aaad5bb8657393eaf0265006eac1dc96691f32e16437");
		this.createItem("Pokémon", "dfa695b59618b3616b6aaa910c5a10146195edd6367d25e9399a74ceef966");
		
		player.openInventory(this.inv);
	}

}
