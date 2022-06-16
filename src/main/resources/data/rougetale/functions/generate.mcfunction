
gamerule commandBlockOutput false
gamerule sendCommandFeedback false
gamerule spawnRadius 1




#
execute unless block 125 225 120 minecraft:grass_block run setblock 100 198 100 minecraft:structure_block{posX:0,posY:1,posZ:0,mode:"LOAD",name:"rougetale:portalfloor"}
execute unless block 125 225 120 minecraft:grass_block run setblock 100 199 100 minecraft:redstone_block
execute unless block 125 225 120 minecraft:grass_block run setblock 52 198 100 minecraft:structure_block{posX:0,posY:1,posZ:0,mode:"LOAD",name:"rougetale:portalfloor"}
execute unless block 125 225 120 minecraft:grass_block run setblock 52 199 100 minecraft:redstone_block
execute unless block 125 225 120 minecraft:grass_block run setblock 148 198 100 minecraft:structure_block{posX:0,posY:1,posZ:0,mode:"LOAD",name:"rougetale:portalfloor"}
execute unless block 125 225 120 minecraft:grass_block run setblock 148 199 100 minecraft:redstone_block
execute unless block 125 225 120 minecraft:grass_block run setblock 100 198 52 minecraft:structure_block{posX:0,posY:1,posZ:0,mode:"LOAD",name:"rougetale:portalfloor"}
execute unless block 125 225 120 minecraft:grass_block run setblock 100 199 52 minecraft:redstone_block
execute unless block 125 225 120 minecraft:grass_block run setblock 100 198 148 minecraft:structure_block{posX:0,posY:1,posZ:0,mode:"LOAD",name:"rougetale:portalfloor"}
execute unless block 125 225 120 minecraft:grass_block run setblock 100 199 148 minecraft:redstone_block
execute unless block 125 225 120 minecraft:grass_block run setblock 52 198 52 minecraft:structure_block{posX:0,posY:1,posZ:0,mode:"LOAD",name:"rougetale:portalfloor"}
execute unless block 125 225 120 minecraft:grass_block run setblock 52 199 52 minecraft:redstone_block
execute unless block 125 225 120 minecraft:grass_block run setblock 100 198 100 minecraft:structure_block{posX:0,posY:1,posZ:0,mode:"LOAD",name:"rougetale:portalfloor"}
execute unless block 125 225 120 minecraft:grass_block run setblock 100 199 100 minecraft:redstone_block
execute unless block 125 225 120 minecraft:grass_block run setblock 148 198 148 minecraft:structure_block{posX:0,posY:1,posZ:0,mode:"LOAD",name:"rougetale:portalfloor"}
execute unless block 125 225 120 minecraft:grass_block run setblock 148 199 148 minecraft:redstone_block
execute unless block 125 225 120 minecraft:grass_block run setblock 52 198 148 minecraft:structure_block{posX:0,posY:1,posZ:0,mode:"LOAD",name:"rougetale:portalfloor"}
execute unless block 125 225 120 minecraft:grass_block run setblock 52 199 148 minecraft:redstone_block
execute unless block 125 225 120 minecraft:grass_block run setblock 148 198 52 minecraft:structure_block{posX:0,posY:1,posZ:0,mode:"LOAD",name:"rougetale:portalfloor"}
execute unless block 125 225 120 minecraft:grass_block run setblock 148 199 52 minecraft:redstone_block

#
setblock 100 201 100 minecraft:air
setblock 100 198 100 minecraft:air
setblock 52 198 100 minecraft:air
setblock 148 198 100 minecraft:air
setblock 100 198 52 minecraft:air
setblock 100 198 148 minecraft:air
setblock 52 198 52 minecraft:air
setblock 100 198 100 minecraft:air
setblock 148 198 148 minecraft:air
setblock 52 198 148 minecraft:air
setblock 148 198 52 minecraft:air
setblock 100 202 100 minecraft:air

#
execute unless block 125 225 120 minecraft:grass_block run setblock 99 228 61 minecraft:structure_block{posX:0,posY:0,posZ:0,mode:"LOAD",name:"rougetale:spawn"}
execute unless block 125 225 120 minecraft:grass_block run setblock 99 229 61 minecraft:redstone_block

execute unless block 125 225 120 minecraft:grass_block run setblock 100 202 100 minecraft:structure_block{posX:0,posY:1,posZ:0,mode:"LOAD",name:"rougetale:hub"}
execute unless block 125 225 120 minecraft:grass_block run setblock 100 201 100 minecraft:redstone_block

#
setworldspawn 109 237 72
