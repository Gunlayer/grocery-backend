UPDATE t_products
SET image_url = CASE name
                    WHEN 'Watermelon' THEN 'https://i.imgur.com/SxRVflC.jpg'
                    WHEN 'Ostrich eggs' THEN 'https://i.imgur.com/5GQPguw.jpg'
                    WHEN 'Avocado' THEN 'https://i.imgur.com/hc47VwF.jpg'
                    WHEN 'Garlic' THEN 'https://i.imgur.com/2LYFfZJ.jpg'
                    WHEN 'Cherry tomato' THEN 'https://i.imgur.com/3KZ3zbI.jpg'
                    WHEN 'Coconut' THEN 'https://i.imgur.com/NpHbTCI.jpg'
                    WHEN 'Rice' THEN 'https://i.imgur.com/tK8k8q3.jpg'
                    WHEN 'Turkey meat' THEN 'https://i.imgur.com/EjAcLVN.jpeg'
                    WHEN 'Rocket' THEN 'https://i.imgur.com/nBiE86q.jpeg'
                    WHEN 'Carrot' THEN 'https://i.imgur.com/aan7azG.jpg'
                    WHEN 'Red onion' THEN 'https://i.imgur.com/iHYhnnV.jpg'
                    WHEN 'White champignon' THEN 'https://i.imgur.com/tjvkduq.jpg'
                    WHEN 'Eggplant' THEN 'https://i.imgur.com/0cZ4cjs.jpg'
                    WHEN 'Mango' THEN 'https://i.imgur.com/IcuMhAc.jpg'
                    WHEN 'Pear' THEN 'https://i.imgur.com/FgkqMa8.jpg'
                    WHEN 'Dragon fruit' THEN 'https://i.imgur.com/ij8lmil.jpg'
                    WHEN 'Blackberry' THEN 'https://i.imgur.com/jvvVzgk.jpg'
                    WHEN 'Blueberry' THEN 'https://i.imgur.com/D1K9nCu.jpg'
                    WHEN 'Apricot' THEN 'https://i.imgur.com/jVBburx.jpeg'
                    WHEN 'Peach' THEN 'https://i.imgur.com/mmKnH6S.jpg'
                    WHEN 'Plum' THEN 'https://i.imgur.com/1B1AoGE.jpg'
    END;