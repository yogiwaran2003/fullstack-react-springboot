import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { createBrowserRouter, RouterProvider, createRoutesFromElements, Route } from 'react-router-dom'
import Contact from './components/Contact.jsx'
import Login, {loginAction} from './components/Login.jsx'
import Cart from './components/Cart.jsx'
import About from './components/About.jsx'
import Home from './components/Home.jsx'
import ErrorPage from './components/ErrorPage.jsx'
import { ProductLoader } from './components/Home.jsx'
import { contactAction } from './components/Contact.jsx'
import ProductDetail from './components/ProductDetail.jsx'
import { ToastContainer, Bounce } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css';
import { CartProvider } from './store/cart-context.jsx'
import { AuthProvider } from './store/auth-context.jsx'
import ProtectedRoute from './components/ProtectedRoute.jsx'
import CheckoutForm from './components/CheckoutForm.jsx'
import Profile from './components/Profile.jsx'
import { profileLoader, profileAction } from './components/Profile.jsx'
import Orders from './components/Orders.jsx'
import AdminOrders from './components/admin/AdminOrders.jsx'
import Messages from './components/admin/Messages.jsx'
import Register, { registerAction } from './components/Register.jsx'
import { loadStripe } from '@stripe/stripe-js'
import { Elements } from '@stripe/react-stripe-js'
import OrderSuccess from './components/OrderSuccess.jsx'
import { ordersLoader } from './components/Orders.jsx'
import { adminOrdersLoader } from './components/admin/AdminOrders.jsx'
import { messagesLoader } from './components/admin/Messages.jsx'
const stripePromise = loadStripe("pk_test_51T4EAaK5Qm2B7pb3aLFe26vVe1KyOf4yhQZcCffLMlhUdqAlX8D9a4gpO9hgrWMIC4tomwCYTpYkNrTeZjQ9LjDo00a7eWAoVx")


const routeDefinition=createRoutesFromElements(
  <Route path="/" element={<App />} errorElement={<ErrorPage />}>
    <Route index element={<Home />} loader={ProductLoader} />
    <Route path="/home" element={<Home />} loader={ProductLoader} />
    <Route path="/about" element={<About />} />
    <Route path="/contact" element={<Contact />} action={contactAction} />
    <Route path="/login" element={<Login />} action={loginAction} />
    <Route path="/register" element={<Register />} action={registerAction} />
    <Route path="/cart" element={<Cart />} />

    <Route path="/products/:productId" element={<ProductDetail />} />
    <Route element={<ProtectedRoute />}>
        <Route path="/checkout" element={<CheckoutForm />} />
        <Route path="/order-success" element={<OrderSuccess />} />
        <Route path="/profile" element={<Profile />} loader={profileLoader} action={profileAction} shouldRevalidate={({actionResult}) => {return !actionResult?.success}}/>
        <Route path="/orders" element={<Orders />} loader={ordersLoader} />
        <Route path="/admin/orders" element={<AdminOrders />} loader={adminOrdersLoader} />
        <Route path="/admin/messages" element={<Messages />} loader={messagesLoader} />
    </Route>
  </Route>
)

const appRouter = createBrowserRouter(routeDefinition);



createRoot(document.getElementById('root')).render(
  <StrictMode>
  <Elements stripe={stripePromise}>
  <AuthProvider>
  <CartProvider>
    <RouterProvider router={appRouter} />
  </CartProvider>
  </AuthProvider>
    <ToastContainer
      position="top-center"
      autoClose={3000}
      hideProgressBar={false}
      newestOnTop={false}
      draggable
      pauseOnHover
      theme={localStorage.getItem("theme")==="dark"?"dark":"light"}
      transition={Bounce}
    />
  </Elements>
  </StrictMode>,
)
