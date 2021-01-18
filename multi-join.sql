SELECT * 
FROM U04jTC.appointment apt
JOIN U04jTC.customer cs
ON apt.customerId = cs.customerId
JOIN U04jTC.address ad
ON cs.addressId = ad.addressId
JOIN U04jTC.city ct
ON ad.cityId = ct.cityId
JOIN U04jTC.country cn
ON ct.countryId = cn.countryId