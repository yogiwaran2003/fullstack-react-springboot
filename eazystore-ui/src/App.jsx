
import { Outlet } from 'react-router-dom';
import './App.css'
import Header from './components/Header';
import Footer from './components/footer/Footer';
import { useNavigation } from 'react-router-dom';
import React from 'react';

function App() {
  const navigation=useNavigation();
  navigation.state
  return (
    <React.Fragment>
    <Header/>
    {navigation.state==="loading" ?(
      <div className="flex items-center justify-center min-h-[852px]">
        <span className="text-4xl font-semibold text-primary dark:text-light">Loading...</span>
      </div>
    ) :(<Outlet/>)}
    
    <Footer/>
    </React.Fragment>
    
  )
}
export default App
