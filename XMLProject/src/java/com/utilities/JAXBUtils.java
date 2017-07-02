package com.utilities;

import BLO.BlockBLO;
import com.entities.Block;
import java.util.List;
import jaxb.classes.BlockItem;
import jaxb.classes.ListBlock;

public class JAXBUtils {
    public static void saveBlockJAXBToDatabase(ListBlock list) {
        List<BlockItem> listBlockItem = list.getBlock();
        for (BlockItem blockItem : listBlockItem) {
            Block block = new Block();
            block.setBlockName(blockItem.getName());
            block.setDescription(blockItem.getDescription());
            block.setActive(true);
            BlockBLO blockBLO = new BlockBLO();
            blockBLO.add(block);
        }
    }
}
