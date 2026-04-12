function Account({ account }) {
  return (
    <div>
        <p>{account.id}</p>
        <p>Amount: {account.amount} czk</p>
    </div>
  );
}

export default Account;