package net.moltendorf.Bukkit.PowerLevel;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

/**
 * Created by moltendorf on 14/09/24.
 *
 * @author moltendorf
 */
public class ItemState {
	public ItemStack item;

	public final String name;
	public final Map<Enchantment, Integer> enchantments;
	public final Material type;

	public short durability;
	public int maxDifference;

	public ItemState(final ItemStack input, final int maxDifference) {
		item = input;

		this.maxDifference = maxDifference;

		type = input.getType();
		durability = input.getDurability();
		enchantments = input.getEnchantments();

		final ItemMeta meta = input.getItemMeta();

		if (meta.hasDisplayName()) {
			name = meta.getDisplayName();
		} else {
			name = null;
		}
	}

	public boolean equals(final ItemStack input) {
		if (type != input.getType()) {
			return false;
		}

		final ItemMeta meta = input.getItemMeta();

		if (meta.hasDisplayName()) {
			if (name == null) {
				return false;
			} else if (!name.equals(meta.getDisplayName())) {
				return false;
			}
		} else if (name != null) {
			return false;
		}

		final Map<Enchantment, Integer> currentEnchantments = input.getEnchantments();

		if (enchantments.size() != currentEnchantments.size()) {
			return false;
		}

		for (final Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
			if (!entry.getValue().equals(currentEnchantments.get(entry.getKey()))) {
				return false;
			}
		}

		return true;
	}

	public boolean update(final ItemStack input) {
		if (equals(input)) {
			item = input;

			return true;
		}

		return false;
	}
}
