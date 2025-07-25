function setup()
    fc.debugging.log("info", "This is so cool!")

    fc.block.register("snow", {r = 255, g = 255, b = 255})
    fc.inventory.addItem("snow")

    fc.event.subscribe("world.block_broken", onBlockBroken)
    fc.event.subscribe("world.block_placed", onBlockPlaced)
    fc.event.subscribe("mouse.pressed", onMousePressed)
end

function update()
end

function onBlockBroken(blockType, posX, posY)
    print("BLOCK BROKEN EVENT")
    print("Type: " .. blockType)
    print("Position: " .. posX .. ", " .. posY)
    print("------------------------")
end

function onBlockPlaced(oldType, newType, posX, posY)
    print("BLOCK PLACED EVENT")
    print("Old type: " .. oldType)
    print("New type: " .. newType)
    print("Position: " .. posX .. ", " .. posY)
    print("------------------------")
end

function onMousePressed(button, x, y)
    print("MOUSE PRESSED: " .. button .. " at " .. x .. ", " .. y)
end