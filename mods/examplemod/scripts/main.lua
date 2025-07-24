function setup()
    fc.debugging.log("info", "This is so cool!")

    fc.block.register("snow", {r = 255, g = 255, b = 255})
    fc.inventory.addItem("snow")

    fc.event.subscribe("mouse.pressed", ipress)
end

function update()
end

function ipress(buttonName, posX, posY)
    if buttonName == "left" then
        print("SO, SO COOL!")
    else
        print("Coordinates is " .. posX .. ", " .. posY)
    end
end