import React, {useState, useEffect} from 'react'
import { request, setAuthHeader, getAuthToken } from '../../Helpers/axios_helper';
import {SliderBox} from "../SliderBox"
import "./css/Cart.css"
import { FixedSizeList as List } from 'react-window';


export const Cart = () => {
    const[results, setResults] = useState([])
    const[wines, setWines] = useState([])
    const[total, setTotal] = useState(0)

    const  calculateTotal = (response) =>{
        let length = response.cartWines.length;
        let temp = 0;

        for(let i = 0; i < length; i++){
            temp += parseInt(response.cartWines[i].wine.price) * parseInt(response.cartWines[i].quantity);
        }
        setTotal(temp)
    }

    const handleOrder = () => {
        request(
          "POST",
          "/orders/order/create",
          {
            headers: {
                Authorization: `Bearer ${getAuthToken()}`,
            },
          }
          ).catch(
          (error) => {
            console.error('Error fetching data:', error);
          }
        );
      }

      const initalWines = () => {
        request(
            "GET",
            "/wines/all",
            ).then(
            (response) => {
                setWines(response.data)
            }).catch(
            (error) => {
              console.error('Error fetching data:', error);
            }
        );
    };

      const initalData = () => {
        request(
            "GET",
            "/cart/view",
            {
                headers: {
                    Authorization: `Bearer ${getAuthToken()}`,
                },  
            }).then(
            (response) => {
                setResults(response.data)
                calculateTotal(response.data);
            }).catch(
            (error) => {
              console.error('Error fetching data:', error);
            }
          );
      };
    
      useEffect(() => {
          initalData();
          initalWines();
      }, []);

      return(
            <div className='cart-outer-container'>
                <div className='cart-inner-container'>
                    <h1>CART</h1>
                    <div className='cart-content'>
                            {results.cartWines && results.cartWines.length > 0 ? (
                                <List height={500} itemCount={results.cartWines.length} itemSize={350} width={600}>
                                    {({ index, style }) => (
                                        // Render your content here based on the index
                                        <div style={style} className='cart-wine'>
                                            <img src={results.cartWines[index].wine.photoUrl} alt={results.cartWines[index].wine.name} />
                                            <div id="wine-details">
                                                <h2>{results.cartWines[index].wine.name.toUpperCase()}</h2>
                                                <p id="single-price">{results.cartWines[index].wine.price} mkd</p>
                                                <p id="total-price">{results.cartWines[index].wine.price} x {results.cartWines[index].quantity} = <span>{(results.cartWines[index].wine.price)*(results.cartWines[index].quantity)} mkd</span></p>
                                                <p id="winery-name">WINERY: <span>{results.cartWines[index].wine.winery.name}</span></p>
                                            </div>
                                        </div>
                                    )}
                                </List>
                                ) : (
                                    <p>No items in the cart.</p>
                            )}
                        <div className='order-confirmation'>
                                <h2>SUMMARY</h2>
                                <p>Total: {total} mkd</p>
                                <p>Delivery: 150 mkd</p>
                                <hr/>
                                <p id="total-price-order">TOTAL: <span>{total + 150} mkd</span></p>
                                <button onClick={handleOrder}>ORDER</button>
                        </div>
                        <div>

                        </div>
                    </div>
                    <hr/>
                    <div className='recommended-container'>
                        <h1>RECOMMENDED</h1>
                        <div className='recommended-list'>
                            <SliderBox results={wines} display="wine"/>
                        </div>
                    </div>                    
                </div>
            </div>
      );

}