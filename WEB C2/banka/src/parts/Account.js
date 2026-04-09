function Account({ account }) {
  return (
    <div>
        <p>{account.id}</p>
        <p>Amount: {account.amount} czk</p>
        <button>View account</button>
    </div>
  );
}

export default Account;