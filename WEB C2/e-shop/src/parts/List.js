import Item from "./Item"

function List ({list, Add}) {
    if (list.length !== 0) {
        return (
            <div className="rounded-3 p-3">
                {list.map((item) => (
                    <Item key={item.id} Name={item.name} Amount={item.amount} Id={item.id} Img={item.img} Add={Add}/>
                ))}
            </div>
        )
    }else {
        return (<h3 className="my-4">Žádné zboží k zobrazení</h3>)
    }
}

export default List;