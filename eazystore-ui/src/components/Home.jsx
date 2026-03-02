// import products from "../data/products";
import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import apiClient from "../api/apiClient";
import { useState , useEffect, use} from "react";
import { useLoaderData } from "react-router-dom";


export default function Home() {
  const products = useLoaderData();
  return (
    <div className="max-w-[1152px] mx-auto px-6 py-8">
      <PageHeading title="Explore Eazy Stickers!">
        Add a touch of creativity to your space with our wide range of fun and
        unique stickers. Perfect for any occasion!
      </PageHeading>
      <ProductListings products={products} />
    </div>
  );
}

export async function ProductLoader(){
  try{
    const response = await apiClient.get("/products");//Axios get request
    return response.data;//Update products state with fetched products
  }catch(error){
    throw new Response(
      error.response?.data?.errorMessage || error.message || "Failed to fetch products. Please try again later.",
      {status: error.status || 500}
    );
  }
}
