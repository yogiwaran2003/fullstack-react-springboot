export default function Price({currency,price}){
    return(
        <>
        {currency}
        <span className="ml-1">{price}</span>
        </>
    )
}