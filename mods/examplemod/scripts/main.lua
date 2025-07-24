function setup()
    fc.debugging.log("info", "This is so cool!")

    fc.block.register("snow", {r = 255, g = 255, b = 255})
    fc.inventory.addItem("snow")
end

function update()
    if fc.event.mouse.isButtonDown("left") then
        print(123)
    end
end